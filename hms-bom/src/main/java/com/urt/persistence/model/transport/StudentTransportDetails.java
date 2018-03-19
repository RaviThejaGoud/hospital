package com.urt.persistence.model.transport;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.churchgroup.util.object.ObjectFunctions;
import com.urt.persistence.model.base.PersistentObject;
import com.urt.persistence.model.common.AcademicYear;
import com.urt.persistence.model.customer.FeeType;
import com.urt.persistence.model.customer.SchoolTerms;
import com.urt.persistence.model.study.Student;
import com.urt.persistence.model.user.User;

/*
 * @create new table customer.
 */
@Entity
@Table(name = "studentTransportDetails")
public class StudentTransportDetails  extends PersistentObject {
	
    private static final long serialVersionUID = 3832626162173359411L;
    
    
    private long custId;
    private AcademicYear academicYear;
    private Student student;
    private RouteBoardingPoints pickupBoardingPoint;
    private RouteBoardingPoints dropBoardingPoint;
    private VehiclesAcademicDetails pickupVehicle;
    private VehiclesAcademicDetails dropVehicle;
    private SchoolTerms schoolTerm;
    private String boardingType;
    private FeeType feeType;
    
	public StudentTransportDetails() {
		this.createdDate = new Date();
        this.lastAccessDate = new Date();
        this.lastUpdatedDate = new Date();
    }

   
	public StudentTransportDetails(long id) {
        setId(id);
    }

	@Override
	public int compareTo(Object object) {
		StudentTransportDetails myClass = (StudentTransportDetails) object;
		return new CompareToBuilder().append(this.getCreatedDate(),
				myClass.getCreatedDate()).append(this.getCreatedById(),
				myClass.getCreatedById()).toComparison();
	}

	public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        return true;
    }

    public int hashCode() {
        return (getStrId() != null ? this.getStrId().hashCode() : 0);
    }

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
        .append("id", this.getId())
        .toString();
	}
	
	
	@Column(name = "custId", nullable = false, length = 10)
	 public long getCustId() {
		return custId;
	}

	public void setCustId(long custId) {
		this.custId = custId;
	}

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "academicYearId", insertable = true, updatable = true)
	public AcademicYear getAcademicYear() {
		return academicYear;
	}


	public void setAcademicYear(AcademicYear academicYear) {
		this.academicYear = academicYear;
	}

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "studentId", insertable = true, updatable = true)
	public Student getStudent() {
		return student;
	}


	public void setStudent(Student student) {
		this.student = student;
	}

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "pickupBoardingPointId", insertable = true, updatable = true)
	public RouteBoardingPoints getPickupBoardingPoint() {
		return pickupBoardingPoint;
	}

	
	public void setPickupBoardingPoint(RouteBoardingPoints pickupBoardingPoint) {
		this.pickupBoardingPoint = pickupBoardingPoint;
	}

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "dropBoardingPointId", insertable = true, updatable = true)
	public RouteBoardingPoints getDropBoardingPoint() {
		return dropBoardingPoint;
	}


	public void setDropBoardingPoint(RouteBoardingPoints dropBoardingPoint) {
		this.dropBoardingPoint = dropBoardingPoint;
	}

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "pickupVehicleId", insertable = true, updatable = true)
	public VehiclesAcademicDetails getPickupVehicle() {
		return pickupVehicle;
	}

	
	public void setPickupVehicle(VehiclesAcademicDetails pickupVehicle) {
		this.pickupVehicle = pickupVehicle;
	}

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "dropVehicleId", insertable = true, updatable = true)
	public VehiclesAcademicDetails getDropVehicle() {
		return dropVehicle;
	}


	public void setDropVehicle(VehiclesAcademicDetails dropVehicle) {
		this.dropVehicle = dropVehicle;
	}

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "termId", insertable = true, updatable = true)
	public SchoolTerms getSchoolTerm() {
		return schoolTerm;
	}


	public void setSchoolTerm(SchoolTerms schoolTerm) {
		this.schoolTerm = schoolTerm;
	}
	
	@Column(name = "boardingType", nullable = false, length = 5,columnDefinition="char(1) default 'Y'")
	public String getBoardingType() {
		return boardingType;
	}

	public void setBoardingType(String boardingType) {
		this.boardingType = boardingType;
	}
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "feeTypeId", insertable = true, updatable = true)
	public FeeType getFeeType() {
		return feeType;
	}


	public void setFeeType(FeeType feeType) {
		this.feeType = feeType;
	}


	@Transient
	public String getPickupDropingAndVechileId(){
		if(!ObjectFunctions.isNullOrEmpty(getPickupBoardingPoint()) && !ObjectFunctions.isNullOrEmpty(getPickupBoardingPoint().getRoute()) 
				&& !ObjectFunctions.isNullOrEmpty(getPickupVehicle()) && !ObjectFunctions.isNullOrEmpty(getPickupVehicle().getVehicle()))
		{
			return this.getPickupBoardingPoint().getId()+"-"+this.getPickupBoardingPoint().getRoute().getId()+"-"+this.getPickupVehicle().getVehicle().getId();
		}
		else 
			return null;
	}
	@Transient
	public String getDropDropingAndVechileId(){
		if(!ObjectFunctions.isNullOrEmpty(getDropBoardingPoint())&&!ObjectFunctions.isNullOrEmpty(getDropBoardingPoint().getRoute())
				&&!ObjectFunctions.isNullOrEmpty(getDropVehicle()) &&!ObjectFunctions.isNullOrEmpty(getDropVehicle().getVehicle())){
			return this.getDropBoardingPoint().getId()+"-"+this.getDropBoardingPoint().getRoute().getId()+"-"+this.getDropVehicle().getVehicle().getId();
		}else
			return null;
		
	}
	
	@Transient
	public double getPickupAndDropFeeAmount(){
		double pickupAmpunt=0;
		double dropAmount=0;
		if(!ObjectFunctions.isNullOrEmpty(this.getPickupBoardingPoint()))
			pickupAmpunt=this.getPickupBoardingPoint().getBoardingPointFeeAmount();
		if(!ObjectFunctions.isNullOrEmpty(this.getDropBoardingPoint()))
			dropAmount=this.getDropBoardingPoint().getBoardingPointFeeAmount();
		return ((pickupAmpunt+dropAmount)/2);
	}
	
}