package com.urt.persistence.model.transport;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.annotations.Type;

import com.churchgroup.util.object.ObjectFunctions;
import com.urt.persistence.model.base.PersistentObject;
import com.urt.persistence.model.study.Student;

/********************************************************************
 * Date              Name               Description
 * ========          ============       ==================
 * July 8, 2013      Rama		        permitnumber added for vehicles
 * 
/********************************************************************/
/*
 * @create new table customer.
 */
@Entity
@Table(name = "vehiclesAcademicDetails")
public class VehiclesAcademicDetails  extends PersistentObject {
	
    private static final long serialVersionUID = 3832626162173359451L;
    
    protected boolean status;
    protected long driverId;
    protected long helperId;
    protected long academicYearId;
    private long availableStudent;
	private long enRolledStudent;
	private long totalStudent;
	protected Set<Route> route;
	protected List<Student> vehicleStudentDetailList;
	private Vehicles vehicle;
	protected String name;
	
	public VehiclesAcademicDetails() {
        this.createdDate = new Date();
        this.lastAccessDate = new Date();
        this.lastUpdatedDate = new Date();
    }

    public VehiclesAcademicDetails(long id) {
        setId(id);
    }
    
    @Column(name = "name", nullable = true, length = 40)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
    
	/**
	 * @return the vehicle
	 */
    @ManyToOne
	@JoinColumn(name="vehicleId", insertable=false, updatable=false)
	public Vehicles getVehicle() {
		return vehicle;
	}

	/**
	 * @param vehicle the vehicle to set
	 */
	public void setVehicle(Vehicles vehicle) {
		this.vehicle = vehicle;
	}

	@Override
	public int compareTo(Object object) {
		VehiclesAcademicDetails myClass = (VehiclesAcademicDetails) object;
		return new CompareToBuilder().append(this.getCreatedDate(),
				myClass.getCreatedDate()).append(this.getCreatedById(),
				myClass.getCreatedById()).toComparison();
	}

	public boolean equals(Object o) {
        if (this == o) 
        	return true;
        if (!(o instanceof VehiclesAcademicDetails)) 
        	return false;
        else
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
        .append("academicYearId", this.academicYearId)     
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

	@Transient
	public long getTotalStudent() {
		return totalStudent;
	}

	public void setTotalStudent(long totalStudent) {
		this.totalStudent = totalStudent;
	}
	@Transient
	public long getAvailableStudent() {
		return availableStudent;
	}

	public void setAvailableStudent(long availableStudent) {
		this.availableStudent = availableStudent;
	}
	@Transient
	public long getEnRolledStudent() {
		return enRolledStudent;
	}

	public void setEnRolledStudent(long enRolledStudent) {
		this.enRolledStudent = enRolledStudent;
	}

	/**
	 * @return the driverId
	 */
	@Column(name = "driverId", nullable = true, length = 10)
	public long getDriverId() {
		return driverId;
	}

	/**
	 * @param driverId the driverId to set
	 */
	public void setDriverId(long driverId) {
		this.driverId = driverId;
	}

	/**
	 * @return the helperId
	 */
	@Column(name = "helperId", nullable = true, length = 10)
	public long getHelperId() {
		return helperId;
	}

	/**
	 * @param helperId the helperId to set
	 */
	public void setHelperId(long helperId) {
		this.helperId = helperId;
	}

	/**
	 * @return the vehicleStudentDetailList
	 */
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
   	@JoinColumn(name="vehicleId") 
   	@javax.xml.bind.annotation.XmlTransient()
	public List<Student> getVehicleStudentDetailList() {
		return vehicleStudentDetailList;
	}

	/**
	 * @param vehicleStudentDetailList the vehicleStudentDetailList to set
	 */
	public void setVehicleStudentDetailList(List<Student> vehicleStudentDetailList) {
		this.vehicleStudentDetailList = vehicleStudentDetailList;
	}
	
	public void addRouteToBStudents(Student student){
		if(ObjectFunctions.isNullOrEmpty(this.getVehicleStudentDetailList())){
			this.vehicleStudentDetailList=new ArrayList<Student>();
		}
		this.vehicleStudentDetailList.add(student);
	}
	public void removeStudent(Student student) {
		getVehicleStudentDetailList().remove(student);
    }

	@Column(name = "academicYearId", nullable = false, length = 10)
	public long getAcademicYearId() {
		return academicYearId;
	}

	public void setAcademicYearId(long academicYearId) {
		this.academicYearId = academicYearId;
	}

	public void setRoute(Set<Route> route) {
		this.route = route;
	}

     /* @ManyToMany(
        cascade = {CascadeType.PERSIST, CascadeType.MERGE},
        mappedBy = "vehicleAcademicDetailsList",
        targetEntity = Route.class
    )*/
	@ManyToMany(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	     @JoinTable(name="RouteWithVehicles",
	        		joinColumns=@JoinColumn(name="vehicleAcademicId"),inverseJoinColumns=@JoinColumn(name="routeId"))
	@javax.xml.bind.annotation.XmlTransient()
    public Set<Route> getRoute() {
        if(ObjectFunctions.isNullOrEmpty(route))
        	route = new HashSet<Route>();
        return this.route;
    }
	public void addRoute(Route route) {
        if(ObjectFunctions.isNullOrEmpty(this.route)){
                this.route=new HashSet<Route>();
        }
        this.route.add(route);
	}
	public void copyFormValues(VehiclesAcademicDetails obj)
    {
		this.driverId=obj.getDriverId();
		this.helperId = obj.getHelperId();
		this.name = obj.getName();
		if(!ObjectFunctions.isNullOrEmpty(obj.getVehicle()) && !ObjectFunctions.isNullOrEmpty(this.vehicle)){
			this.vehicle.copyFormValues(obj.getVehicle());
		}
    }
	@Transient
    public String getVehicleNameAndVehicleNumber()
	{
		if(ObjectFunctions.isNullOrEmpty(this.vehicle))
			return getName();
		else
			return getName()+" - "+this.vehicle.getVehicleNumber();
    }
}
    

  

