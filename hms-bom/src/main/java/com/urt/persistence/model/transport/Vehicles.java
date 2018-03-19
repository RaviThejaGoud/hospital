package com.urt.persistence.model.transport;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.churchgroup.util.date.DateFormatter;
import com.churchgroup.util.object.ObjectFunctions;
import com.urt.persistence.model.base.PersistentObject;
import com.urt.persistence.model.user.User;

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
@Table(name = "vehicles")
public class Vehicles  extends PersistentObject {
	
    private static final long serialVersionUID = 3832626162173359411L;
    
    
    protected String vehicleType;  
    protected String vehicleNumber;
    protected long noOfSeats;
    //protected boolean status;
    protected String insuranceNumber;
    protected Date insurancePaidDate;
    protected Date insuranceExpiredDate;     
    //protected Set<VehicleTrip> vehicleTrip;
    protected String classificationType;
    protected String ownerName;
   // protected String registrationNumber;
    protected String chasisNumber;
    protected String engineNumber;
    //protected long driverId;
    //protected long helperId;
    protected Long routeId;
    
    private long availableStudent;
	private long enRolledStudent;
	private long totalStudent;
	protected long custId;
	/*protected long academicYearId;*/
	
	protected String roadTaxAmount;
	protected Date roadTaxPaidDate;
	protected Date roadTaxNextPaymentDate;
	
	protected Date pollutionCheckedDate;
	protected Date pollutionExpiryDate;
	
	protected Date fitnessCheckDate;
	protected Date fitnessExpiryDate;
	
	protected Date permitCheckedDate;	
	protected Date permitExpiryDate;
	//protected Set<Route> route;
	
	protected String vehicleMaker;
	protected String registrationAuthority;
	protected String insuranceDetails;
	
	//protected List<Student> vehicleStudentDetailList;
    protected String permitNumber;
    private List<VehiclesAcademicDetails> vehiclesAcademicDetailsList;

    /**
	 * @return the permitNumber
	 */
	@Column(name = "permitNumber", nullable = true, length = 20)
	public String getPermitNumber() {
		return permitNumber;
	}

	public void setPermitNumber(String permitNumber) {
		this.permitNumber = permitNumber;
	}

	public Vehicles() {
        
    }

    public Vehicles(long id) {
        setId(id);
    }
    
    
    
	/**
	 * @return the vehiclesAcademicDetailsList
	 */
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="vehicleId")
	@javax.xml.bind.annotation.XmlTransient()
	public List<VehiclesAcademicDetails> getVehiclesAcademicDetailsList() {
		return vehiclesAcademicDetailsList;
	}

	/**
	 * @param vehiclesAcademicDetailsList the vehiclesAcademicDetailsList to set
	 */
	public void setVehiclesAcademicDetailsList(
			List<VehiclesAcademicDetails> vehiclesAcademicDetailsList) {
		this.vehiclesAcademicDetailsList = vehiclesAcademicDetailsList;
	}

	@Override
	public int compareTo(Object object) {
		Vehicles myClass = (Vehicles) object;
		return new CompareToBuilder().append(this.getVehicleType(),
				myClass.getVehicleType()).append(this.getCreatedDate(),
				myClass.getCreatedDate()).append(this.getCreatedById(),
				myClass.getCreatedById()).toComparison();
	}

	public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        final Vehicles transport = (Vehicles) o;

        if (vehicleType != null ? !vehicleType.equals(transport.getVehicleType()) : transport.getVehicleType() != null) return false;

        return true;
    }

    public int hashCode() {
        return (getStrId() != null ? this.getStrId().hashCode() : 0);
    }

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
        .append("id", this.getId())
        .append("insuranceNumber", this.insuranceNumber)     
        .toString();
	}
	
	
	@Column(name = "vehicleType", nullable = true, length = 40)
	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}
	@Column(name = "vehicleNumber", nullable = true )
	public String getVehicleNumber() {
		return vehicleNumber;
	}

	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}
	@Column(name = "noOfSeats", nullable = false, length = 10)
	public long getNoOfSeats() {
		return noOfSeats;
	}

	public void setNoOfSeats(long noOfSeats) {
		this.noOfSeats = noOfSeats;
	}

	/*@Column(name = "status", nullable = false, length = 1, columnDefinition="char(1) default 'Y'")
	    @Type(type="yes_no")
	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}*/

	@Column(name = "insuranceNumber", nullable = true, length = 20)
	public String getInsuranceNumber() {
		return insuranceNumber;
	}

	public void setInsuranceNumber(String insuranceNumber) {
		this.insuranceNumber = insuranceNumber;
	}
	/* @Column(name = "insuranceExpiredDate", nullable = true, length = 12)
	public String getInsuranceExpiredDate() {
		return insuranceExpiredDate;
	}

	public void setInsuranceExpiredDate(String insuranceExpiredDate) {
		this.insuranceExpiredDate = insuranceExpiredDate;
	}*/
	
	public Date getInsuranceExpiredDate() {
		return insuranceExpiredDate;
	}

	public void setInsuranceExpiredDate(Date insuranceExpiredDate) {
		this.insuranceExpiredDate = insuranceExpiredDate;
	}

	/*@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="vehicleId") 
	public Set<VehicleTrip> getVehicleTrip() {
		return vehicleTrip;
	}

	public void setVehicleTrip(Set<VehicleTrip> vehicleTrip) {
		this.vehicleTrip = vehicleTrip;
	}
	public void addVehicleTrip(VehicleTrip vehicleTrip){
		if(ObjectFunctions.isNullOrEmpty(this.getVehicleTrip())){
			this.vehicleTrip=new HashSet<VehicleTrip>();
		}
		this.vehicleTrip.add(vehicleTrip);
	}*/
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
	 * @return the classificationType
	 */
	@Column(name = "classificationType", nullable = true, length = 40)
	public String getClassificationType() {
		return classificationType;
	}

	/**
	 * @param classificationType the classificationType to set
	 */
	public void setClassificationType(String classificationType) {
		this.classificationType = classificationType;
	}

	/**
	 * @return the ownerName
	 */
	@Column(name = "ownerName", nullable = true, length = 40)
	public String getOwnerName() {
		return ownerName;
	}

	/**
	 * @param ownerName the ownerName to set
	 */
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	/**
	 * @return the registrationNumber
	 *//*
	@Column(name = "registrationNumber", nullable = true, length = 12)
	public String getRegistrationNumber() {
		return registrationNumber;
	}

	*//**
	 * @param registrationNumber the registrationNumber to set
	 *//*
	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}*/

	/**
	 * @return the chasisNumber
	 */
	@Column(name = "chasisNumber", nullable = true, length = 20)
	public String getChasisNumber() {
		return chasisNumber;
	}

	/**
	 * @param chasisNumber the chasisNumber to set
	 */
	public void setChasisNumber(String chasisNumber) {
		this.chasisNumber = chasisNumber;
	}

	/**
	 * @return the engineNumber
	 */
	@Column(name = "engineNumber", nullable = true, length = 40)
	public String getEngineNumber() {
		return engineNumber;
	}

	/**
	 * @param engineNumber the engineNumber to set
	 */
	public void setEngineNumber(String engineNumber) {
		this.engineNumber = engineNumber;
	}

/*	@Column(name = "driverId", nullable = true, length = 10)
	public long getDriverId() {
		return driverId;
	}

	public void setDriverId(long driverId) {
		this.driverId = driverId;
	}*/
/*
	@Column(name = "helperId", nullable = true, length = 10)
	public long getHelperId() {
		return helperId;
	}
	public void setHelperId(long helperId) {
		this.helperId = helperId;
	}*/

	/*@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
   	@JoinColumn(name="vehicleId") 
	public List<Student> getVehicleStudentDetailList() {
		return vehicleStudentDetailList;
	}

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
    }*/
	@Column(name = "custId", nullable = false, length = 10)
	 public long getCustId() {
		return custId;
	}

	public void setCustId(long custId) {
		this.custId = custId;
	}

	/**
	 * @return the routeId
	 */
	public Long getRouteId() {
		return routeId;
	}

	/**
	 * @param routeId the routeId to set
	 */
	public void setRouteId(Long routeId) {
		this.routeId = routeId;
	}
	/*@Column(name = "academicYearId", nullable = false, length = 10)
	public long getAcademicYearId() {
		return academicYearId;
	}*/

	@Column(name = "roadTaxAmount", nullable = false, length = 10)
	public String getRoadTaxAmount() {
		return roadTaxAmount;
	}

	public void setRoadTaxAmount(String roadTaxAmount) {
		this.roadTaxAmount = roadTaxAmount;
	}
  
 

	public Date getRoadTaxPaidDate() {
		return roadTaxPaidDate;
	}

	public void setRoadTaxPaidDate(Date roadTaxPaidDate) {
		this.roadTaxPaidDate = roadTaxPaidDate;
	}

	public Date getRoadTaxNextPaymentDate() {
		return roadTaxNextPaymentDate;
	}

	public void setRoadTaxNextPaymentDate(Date roadTaxNextPaymentDate) {
		this.roadTaxNextPaymentDate = roadTaxNextPaymentDate;
	}

	public Date getPollutionCheckedDate() {
		return pollutionCheckedDate;
	}

	public void setPollutionCheckedDate(Date pollutionCheckedDate) {
		this.pollutionCheckedDate = pollutionCheckedDate;
	}

	public Date getPollutionExpiryDate() {
		return pollutionExpiryDate;
	}

	public void setPollutionExpiryDate(Date pollutionExpiryDate) {
		this.pollutionExpiryDate = pollutionExpiryDate;
	}

	public Date getFitnessCheckDate() {
		return fitnessCheckDate;
	}

	public void setFitnessCheckDate(Date fitnessCheckDate) {
		this.fitnessCheckDate = fitnessCheckDate;
	}

	public Date getFitnessExpiryDate() {
		return fitnessExpiryDate;
	}

	public void setFitnessExpiryDate(Date fitnessExpiryDate) {
		this.fitnessExpiryDate = fitnessExpiryDate;
	}

	public Date getPermitCheckedDate() {
		return permitCheckedDate;
	}

	public void setPermitCheckedDate(Date permitCheckedDate) {
		this.permitCheckedDate = permitCheckedDate;
	}

	public Date getPermitExpiryDate() {
		return permitExpiryDate;
	}

	public void setPermitExpiryDate(Date permitExpiryDate) {
		this.permitExpiryDate = permitExpiryDate;
	}

	public Date getInsurancePaidDate() {
		return insurancePaidDate;
	}

	public void setInsurancePaidDate(Date insurancePaidDate) {
		this.insurancePaidDate = insurancePaidDate;
	}
	@Column(name = "vehicleMaker", nullable = true, length = 120)
	public String getVehicleMaker() {
		return vehicleMaker;
	}

	public void setVehicleMaker(String vehicleMaker) {
		this.vehicleMaker = vehicleMaker;
	}
	@Column(name = "registrationAuthority", nullable = true, length = 240)
	public String getRegistrationAuthority() {
		return registrationAuthority;
	}

	public void setRegistrationAuthority(String registrationAuthority) {
		this.registrationAuthority = registrationAuthority;
	}
	@Column(name = "insuranceDetails", nullable = true, length = 1024)
	public String getInsuranceDetails() {
		return insuranceDetails;
	}

	public void setInsuranceDetails(String insuranceDetails) {
		this.insuranceDetails = insuranceDetails;
	}

	/*public void setAcademicYearId(long academicYearId) {
		this.academicYearId = academicYearId;
	}*/
	@Transient
    public String getVehicleTypeAndVehicleNumber() {
	return getVehicleType()+" - "+getVehicleNumber();
    }
	

	public void addVehicleAcademicDetails(VehiclesAcademicDetails vehicleAcademicDetails) {
        if(ObjectFunctions.isNullOrEmpty(this.vehiclesAcademicDetailsList)){
                this.vehiclesAcademicDetailsList=new ArrayList<VehiclesAcademicDetails>();
        }
        this.vehiclesAcademicDetailsList.add(vehicleAcademicDetails);
	}
	@Transient
    public String getInsurancePaidDateStr()
    {
        return DateFormatter.formatDate(DateFormatter.MMDDCCYY_PATTERN, getInsurancePaidDate()); 
    }
	@Transient
    public String getInsuranceExpiredDateStr()
    {
        return DateFormatter.formatDate(DateFormatter.MMDDCCYY_PATTERN, getInsuranceExpiredDate()); 
    }
	@Transient
    public String getPollutionCheckedDateStr()
    {
        return DateFormatter.formatDate(DateFormatter.MMDDCCYY_PATTERN, getPollutionCheckedDate()); 
    }
	@Transient
    public String getPollutionExpiryDateStr()
    {
        return DateFormatter.formatDate(DateFormatter.MMDDCCYY_PATTERN, getPollutionExpiryDate()); 
    }
	@Transient
    public String getFitnessCheckDateStr()
    {
        return DateFormatter.formatDate(DateFormatter.MMDDCCYY_PATTERN, getFitnessCheckDate()); 
    }
	@Transient
    public String getFitnessExpiryDateStr()
    {
        return DateFormatter.formatDate(DateFormatter.MMDDCCYY_PATTERN, getFitnessExpiryDate()); 
    }
	@Transient
    public String getPermitCheckedDateStr()
    {
        return DateFormatter.formatDate(DateFormatter.MMDDCCYY_PATTERN, getPermitCheckedDate()); 
    }
	@Transient
    public String getPermitExpiryDateStr()
    {
        return DateFormatter.formatDate(DateFormatter.MMDDCCYY_PATTERN, getPermitExpiryDate()); 
    }
	@Transient
    public String getRoadTaxPaidDateStr()
    {
        return DateFormatter.formatDate(DateFormatter.MMDDCCYY_PATTERN, getRoadTaxPaidDate()); 
    }
	@Transient
    public String getRoadTaxNextPaymentDateStr()
    {
        return DateFormatter.formatDate(DateFormatter.MMDDCCYY_PATTERN, getRoadTaxNextPaymentDate()); 
    }
	
	/*public void setRoute(Set<Route> route) {
		this.route = route;
	}*/
	
	/*@ManyToMany 
    @JoinTable(name = "RouteWithVehicles",
       joinColumns = { @JoinColumn(name = "vehicleId") },
       inverseJoinColumns = { @JoinColumn(name = "routeId") })

    public Set<Route> getRoute() {
        if(route == null)
        {
        	route = new HashSet<Route>();
        }
        return this.route;
    }
	public void addRoute(Route route) {
        if(ObjectFunctions.isNullOrEmpty(this.route)){
                this.route=new HashSet<Route>();
        }
        this.route.add(route);
	}*/
	
	
	/*@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
 	@JoinColumn(name="vehicleId")
	public Set<TransportMaintenance> getTransportMaintenance() {
		return transportMaintenance;
	}
	public void setTransportMaintenance(
			Set<TransportMaintenance> transportMaintenance) {
		this.transportMaintenance = transportMaintenance;
	}
	 public void addTMSMaintenanceCost(TransportMaintenance transportMaintenance) {
        if(ObjectFunctions.isNullOrEmpty(getTransportMaintenance())){
          this.transportMaintenance=new HashSet<TransportMaintenance>();
        }
        getTransportMaintenance().add(transportMaintenance);
    }*/
	public void copyFormValues(Vehicles vehicle){
		this.chasisNumber = vehicle.getChasisNumber();
		this.classificationType = vehicle.getClassificationType();
		this.engineNumber = vehicle.getEngineNumber();
		this.fitnessCheckDate = vehicle.getFitnessCheckDate();
		this.fitnessExpiryDate = vehicle.getFitnessExpiryDate();
		this.insuranceDetails = vehicle.getInsuranceDetails();
		this.insuranceExpiredDate = vehicle.getInsuranceExpiredDate();
		this.insuranceNumber = vehicle.getInsuranceNumber();
		this.insurancePaidDate = vehicle.getInsurancePaidDate();
		this.noOfSeats = vehicle.getNoOfSeats();
		this.ownerName = vehicle.getOwnerName();
		this.permitCheckedDate = vehicle.getPermitCheckedDate();
		this.permitExpiryDate = vehicle.getPermitExpiryDate();
		this.permitNumber = vehicle.getPermitNumber();
		this.pollutionCheckedDate = vehicle.getPollutionCheckedDate();
		this.pollutionExpiryDate = vehicle.getPollutionExpiryDate();
		this.registrationAuthority = vehicle.getRegistrationAuthority();
		this.roadTaxAmount = vehicle.getRoadTaxAmount();
		this.roadTaxNextPaymentDate = vehicle.getRoadTaxNextPaymentDate();
		this.roadTaxPaidDate=vehicle.getRoadTaxPaidDate();
		this.vehicleMaker=vehicle.getVehicleMaker();
		this.vehicleNumber=vehicle.getVehicleNumber();
		this.vehicleType = vehicle.getVehicleType();
	}
}
    

  

