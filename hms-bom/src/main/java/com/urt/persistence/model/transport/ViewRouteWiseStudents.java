package com.urt.persistence.model.transport;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * ViewClassSectionTeacher entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="vw_routeWiseStudents")

public class ViewRouteWiseStudents  implements java.io.Serializable,Comparable {


    // Fields    
	private static final long serialVersionUID = 3832626162173359411L;
	private long routeId;
	private String routeName;
	private String routePointName;
	private String status;
	private long academicYearId;
	private long boardingPointId;
	private int boardingPointWiseStudsCount;
	private String boardingPointName;

    /** default constructor */
    public ViewRouteWiseStudents() {
    }

    
    

	
	
   /**
	 * @return the boardingPointName
	 */
	public String getBoardingPointName() {
		return boardingPointName;
	}






	/**
	 * @param boardingPointName the boardingPointName to set
	 */
	public void setBoardingPointName(String boardingPointName) {
		this.boardingPointName = boardingPointName;
	}






/**
	 * @return the routeId
	 */
    
	public long getRouteId() {
		return routeId;
	}




	/**
	 * @param routeId the routeId to set
	 */
	public void setRouteId(long routeId) {
		this.routeId = routeId;
	}




	/**
	 * @return the routeName
	 */
	public String getRouteName() {
		return routeName;
	}




	/**
	 * @param routeName the routeName to set
	 */
	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}




	/**
	 * @return the routePointName
	 */
	public String getRoutePointName() {
		return routePointName;
	}




	/**
	 * @param routePointName the routePointName to set
	 */
	public void setRoutePointName(String routePointName) {
		this.routePointName = routePointName;
	}




	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}




	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}




	/**
	 * @return the academicYearId
	 */
	public long getAcademicYearId() {
		return academicYearId;
	}




	/**
	 * @param academicYearId the academicYearId to set
	 */
	public void setAcademicYearId(long academicYearId) {
		this.academicYearId = academicYearId;
	}




	/**
	 * @return the boardingPointId
	 */
	@Id
	@Column(name="boardingPointId",nullable=false,updatable=false,unique=true)
	public long getBoardingPointId() {
		return boardingPointId;
	}




	/**
	 * @param boardingPointId the boardingPointId to set
	 */
	public void setBoardingPointId(long boardingPointId) {
		this.boardingPointId = boardingPointId;
	}




	/**
	 * @return the boardingPointWiseStudsCount
	 */
	public int getBoardingPointWiseStudsCount() {
		return boardingPointWiseStudsCount;
	}

	/**
	 * @param boardingPointWiseStudsCount the boardingPointWiseStudsCount to set
	 */
	public void setBoardingPointWiseStudsCount(int boardingPointWiseStudsCount) {
		this.boardingPointWiseStudsCount = boardingPointWiseStudsCount;
	}


   public int compareTo(Object object) {
	   ViewRouteWiseStudents target = (ViewRouteWiseStudents) object;
	   	if (target != null && this != null)
	       {
	   		if(this.getBoardingPointId() >= target.getBoardingPointId())
					return 1;
			else
				return 0;
	       }
			return 0;
	}
   
}