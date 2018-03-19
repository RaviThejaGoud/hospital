package com.urt.persistence.model.transport;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.annotations.Type;

import com.churchgroup.util.object.ObjectFunctions;
import com.hyniva.sms.ws.vo.RouteVo;
import com.urt.persistence.model.base.PersistentObject;
import com.urt.persistence.model.user.User;

/*
 * @create new table customer.
 */
@Entity
@Table(name = "route")
public class Route  extends PersistentObject {
	
    private static final long serialVersionUID = 3832626162173359411L;
    
    protected boolean status;
    protected String routePointName;
    protected String routeEndName;
    protected String routeName;   
    protected String routePointStartTime;  
    protected String routePointEndTime;  
    protected String routePointReturnStartTime;  
    protected String routePointReturnEndTime;
    protected String routePoints;
    protected double routePointFeeAmount;
    //protected Set<Vehicles> vehiclesList;
    protected List<RouteBoardingPoints> routeBoardingPointList;
    protected long custId;
    protected long academicYearId;
    protected long routeStartTimeinMns;
    protected long routeEndTimeinMns;
    
    private List<ViewRouteWiseStudents> routeWiseStudents;
    private Set<VehiclesAcademicDetails> vehicleAcademicDetailsList;
    private long overAllCount;
    
   /**
	 * @return the vehicleAcademicDetailsList
	 */
    /*@ManyToMany(targetEntity=com.urt.persistence.model.transport.VehiclesAcademicDetails.class,
            cascade={CascadeType.PERSIST, CascadeType.MERGE})
        @JoinTable(name="RouteWithVehicles",joinColumns=@JoinColumn(name="routeId"),inverseJoinColumns=@JoinColumn(name="vehicleAcademicId")
        )*/
    @ManyToMany(mappedBy="route") 
    @javax.xml.bind.annotation.XmlTransient()
	public Set<VehiclesAcademicDetails> getVehicleAcademicDetailsList() {
		return vehicleAcademicDetailsList;
	}

	public void setVehicleAcademicDetailsList(
			Set<VehiclesAcademicDetails> vehicleAcademicDetailsList) {
		this.vehicleAcademicDetailsList = vehicleAcademicDetailsList;
	}

	@Column(name = "routePoints", nullable = true, columnDefinition="varchar(3000)")
	public String getRoutePoints() {
		return routePoints;
	}

	public void setRoutePoints(String routePoints) {
		this.routePoints = routePoints;
	}

/**
	 * @return the routeWiseStudents
	 */
   /* @OneToMany(fetch=FetchType.LAZY)
	@JoinColumn(name="routeId",referencedColumnName="id")
	@javax.xml.bind.annotation.XmlTransient()*/
    @Transient
	public List<ViewRouteWiseStudents> getRouteWiseStudents() {
		return routeWiseStudents;
	}


	/**
	 * @param routeWiseStudents the routeWiseStudents to set
	 */
	public void setRouteWiseStudents(List<ViewRouteWiseStudents> routeWiseStudents) {
		this.routeWiseStudents = routeWiseStudents;
	}


	/* protected long routeId;
    
    
    
    // protected Set<VehicleRecord> vehicleRecordDetails;
    
    
    
    *//**
	 * @return the routeId
	 *//*
	public long getRouteId() {
		return routeId;
	}

	*//**
	 * @param routeId the routeId to set
	 *//*
	public void setRouteId(long routeId) {
		this.routeId = routeId;
	}
	*/
    
	public Route() {
		this.createdDate = new Date();
        this.lastAccessDate = new Date();
        this.lastUpdatedDate = new Date();
    }

   
	public Route(long id) {
        setId(id);
    }

	@Override
	public int compareTo(Object object) {
		Route myClass = (Route) object;
		return new CompareToBuilder().append(this.getRoutePointName(),
				myClass.getRoutePointName()).append(this.getCreatedDate(),
				myClass.getCreatedDate()).append(this.getCreatedById(),
				myClass.getCreatedById()).toComparison();
	}

	public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        final Route transport = (Route) o;

        if (routePointName != null ? !routePointName.equals(transport.getRoutePointName()) : transport.getRoutePointName() != null) return false;

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
        .append("routePointName", this.routePointName)
        .append("routeEndName", this.routeEndName)
        .toString();
	}
	
	@Column(name = "status", nullable = false, length = 1, columnDefinition="char(1) default 'A'")
    @Type(type="yes_no")
	public boolean getStatus() {
		return status;
	}
	
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	
	/*@Column(name = "routeNumber", nullable = false, length = 20)
	public String getRouteNumber() {
		return routeNumber;
	}

	public void setRouteNumber(String routeNumber) {
		this.routeNumber = routeNumber;
	}*/
	@Column(name = "routeName", nullable = true, length = 20)
	public String getRouteName() {
		return routeName;
	}

	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}
	@Column(name = "custId", nullable = false, length = 10)
	 public long getCustId() {
		return custId;
	}

	public void setCustId(long custId) {
		this.custId = custId;
	}


	/**
	 * @return the routePointStartTime
	 */
	public String getRoutePointStartTime() {
		return routePointStartTime;
	}


	/**
	 * @param routePointStartTime the routePointStartTime to set
	 */
	
	public void setRoutePointStartTime(String routePointStartTime) {
		this.routePointStartTime = routePointStartTime;
	}


	/**
	 * @return the routePointEndTime
	 */
	public String getRoutePointEndTime() {
		return routePointEndTime;
	}


	/**
	 * @param routePointEndTime the routePointEndTime to set
	 */
	public void setRoutePointEndTime(String routePointEndTime) {
		this.routePointEndTime = routePointEndTime;
	}


	/**
	 * @return the routePointReturnStartTime
	 */
	public String getRoutePointReturnStartTime() {
		return routePointReturnStartTime;
	}


	/**
	 * @param routePointReturnStartTime the routePointReturnStartTime to set
	 */
	public void setRoutePointReturnStartTime(String routePointReturnStartTime) {
		this.routePointReturnStartTime = routePointReturnStartTime;
	}


	/**
	 * @return the routePointReturnEndTime
	 */
	public String getRoutePointReturnEndTime() {
		return routePointReturnEndTime;
	}


	/**
	 * @param routePointReturnEndTime the routePointReturnEndTime to set
	 */
	public void setRoutePointReturnEndTime(String routePointReturnEndTime) {
		this.routePointReturnEndTime = routePointReturnEndTime;
	}


	/**
	 * @return the vehiclesList
	 */
	/*@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="routeId") 
	public Set<Vehicles> getVehiclesList() {
		return vehiclesList;
	}


	*//**
	 * @param vehiclesList the vehiclesList to set
	 *//*
	public void setVehiclesList(Set<Vehicles> vehiclesList) {
		this.vehiclesList = vehiclesList;
	}
	public void addRouteToVehicles(Vehicles vehicles) {
		if(ObjectFunctions.isNullOrEmpty(this.getVehiclesList())){
			this.vehiclesList=new HashSet<Vehicles>();
		}
		this.vehiclesList.add(vehicles);
	}
	public void removeVehiclesToRoute(Vehicles vehicles) {
		if(ObjectFunctions.isNotNullOrEmpty(this.vehiclesList)){
			this.vehiclesList.remove(vehicles);
		}
	}*/

	/**
	 * @return the routeBoardingPointList
	 */
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
   	@JoinColumn(name="routeId") 
   	@javax.xml.bind.annotation.XmlTransient()
	public List<RouteBoardingPoints> getRouteBoardingPointList() {
		return routeBoardingPointList;
	}


	/**
	 * @param routeBoardingPointList the routeBoardingPointList to set
	 */
	public void setRouteBoardingPointList(List<RouteBoardingPoints> routeBoardingPointList) {
		this.routeBoardingPointList = routeBoardingPointList;
	}
	
	
	public void addRouteToBoardingPoints(RouteBoardingPoints routeBoardingPoints){
		if(ObjectFunctions.isNullOrEmpty(this.getRouteBoardingPointList())){
			this.routeBoardingPointList=new ArrayList<RouteBoardingPoints>();
		}
		this.routeBoardingPointList.add(routeBoardingPoints);
	}

	/**
	 * @return the routePointName
	 */
	@Column(name = "routePointName", nullable = true, length = 40)
	public String getRoutePointName() {
		return routePointName;
	}


	/**
	 * @param routePointName the routePointName to set
	 */
	public void setRoutePointName(String routePointName) {
		this.routePointName = routePointName;
	}



/*	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "vehicleRecords", joinColumns = { @JoinColumn(name = "routeId") }, inverseJoinColumns = { @JoinColumn(name = "vehicleId") })
	public Set<VehicleRecord> getVehicleRecordDetails() {
		return vehicleRecordDetails;
	}

	public void setVehicleRecordDetails(Set<VehicleRecord> vehicleRecordDetails) {
		this.vehicleRecordDetails = vehicleRecordDetails;
	}
	public void addVehicleRecordDetails(VehicleRecord vehicleRecordDetails) {
		getVehicleRecordDetails().add(vehicleRecordDetails);
        
    }*/
	@Column(name = "academicYearId", nullable = false, length = 10)
	public long getAcademicYearId() {
		return academicYearId;
	}


	public void setAcademicYearId(long academicYearId) {
		this.academicYearId = academicYearId;
	}

	@Column(name = "routeEndName", nullable = false, length = 120)
	public String getRouteEndName() {
		return routeEndName;
	}


	public void setRouteEndName(String routeEndName) {
		this.routeEndName = routeEndName;
	}

	@Column(name = "routePointFeeAmount",nullable = false,columnDefinition="double default 0")
	public double getRoutePointFeeAmount() {
		return routePointFeeAmount;
	}


	public void setRoutePointFeeAmount(double routePointFeeAmount) {
		this.routePointFeeAmount = routePointFeeAmount;
	}
	

	@Column(name = "routeStartTimeInMns",nullable = false,columnDefinition="double default 0")
	public long getRouteStartTimeinMns() {
		return routeStartTimeinMns;
	}


	public void setRouteStartTimeinMns(long routeStartTimeinMns) {
		this.routeStartTimeinMns = routeStartTimeinMns;
	}

	@Column(name = "routeEndTimeInMns",nullable = false,columnDefinition="double default 0")
	public long getRouteEndTimeinMns() {
		return routeEndTimeinMns;
	}


	public void setRouteEndTimeinMns(long routeEndTimeinMns) {
		this.routeEndTimeinMns = routeEndTimeinMns;
	}
	
	@Transient
	public String getRouteBoardingPointIds() {
		StringBuffer boardingPointIds = new StringBuffer();
		if (ObjectFunctions.isNullOrEmpty(this.routeBoardingPointList)) {
			return boardingPointIds.append("(0)").toString();
		} else {
			boardingPointIds.append("(");
			for (RouteBoardingPoints boardingPoint : this.routeBoardingPointList) {
				boardingPointIds.append(boardingPoint.getId());
				boardingPointIds.append(", ");
			}
			boardingPointIds.append("0)");
			return boardingPointIds.toString();
		}
	}
	@Transient
	public int getRouteCapacity()
	{
		long boardingPointId = -1;
		int total=0;
		if(!ObjectFunctions.isNullOrEmpty(getRouteWiseStudents()))
		{
			for(ViewRouteWiseStudents ct: getRouteWiseStudents())
			{
				if(ct.getBoardingPointId() != boardingPointId){
					total+=ct.getBoardingPointWiseStudsCount();
					boardingPointId = ct.getBoardingPointId();
				}
			}
		}
		return total;
	}
	public void copyFrom(Route route){
		this.custId = route.getCustId();
		this.routeEndName = route.getRouteEndName();
		this.routeEndTimeinMns = route.getRouteEndTimeinMns();
		this.routeName = route.getRouteName();
		this.routePointEndTime = route.getRoutePointEndTime();
		this.routePointFeeAmount = route.getRoutePointFeeAmount();
		this.routePointName = route.getRoutePointName();
		this.routePointReturnEndTime = route.getRoutePointReturnEndTime();
		this.routePointReturnStartTime = route.getRoutePointReturnStartTime();
		this.routePointStartTime = route.getRoutePointStartTime();
		this.routeStartTimeinMns = route.getRouteStartTimeinMns();
		this.status = route.getStatus();
	}
	public RouteVo copyFromEntityToVo(Route route)
	{
		RouteVo routeVo = new RouteVo();
		
		routeVo.id = route.id;
		routeVo.status = route.status;
		routeVo.routePointName = route.routePointName;
		routeVo.routeEndName = route.routeEndName;
		routeVo.routeName = route.routeName;
		routeVo.routePointStartTime = route.routePointStartTime;
		routeVo.routePointEndTime = route.routePointEndTime;
		routeVo.routePointReturnStartTime = route.routePointReturnStartTime;
		routeVo.routePointReturnEndTime = route.routePointReturnEndTime;
		routeVo.routePointFeeAmount = route.routePointFeeAmount;
		routeVo.custId = route.custId;
		routeVo.academicYearId = route.academicYearId;
		routeVo.routeStartTimeinMns = route.routeStartTimeinMns;
		routeVo.routeEndTimeinMns = route.routeEndTimeinMns;
		
		return routeVo;
	}	
	@Transient
	public Double getRouteBoardingPointAmounts() {
		double boardingPointAmount = 0;
		if (ObjectFunctions.isNullOrEmpty(this.routeBoardingPointList)) {
			return 0.0;
		} else {
			for (RouteBoardingPoints boardingPoint : this.routeBoardingPointList) {
				boardingPointAmount=boardingPointAmount+boardingPoint.getBoardingPointFeeAmount();
			}
			return boardingPointAmount;
		}
	}
	@Transient
	public long getOverAllCount() {
		return overAllCount;
	}

	public void setOverAllCount(long overAllCount) {
		this.overAllCount = overAllCount;
	}
	
	
	
}