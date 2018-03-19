package com.urt.persistence.model.transport;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.urt.persistence.model.base.PersistentObject;
import com.urt.persistence.model.user.User;

/*
 * @create new table routeTrack.
 */
@Entity
@Table(name = "routeTrack")
public class RouteTrack  extends PersistentObject {
	
    private static final long serialVersionUID = 3832626162173359411L;

    protected double latitude;
    protected double longitude;
    protected String address;   
    protected Date time;
    protected double speed;
    protected User driver;
    private Route route;
    
    @ManyToOne
	@JoinColumn(name="driverId", insertable=true, updatable=true)
    public User getDriver() {
		return driver;
	}
	public void setDriver(User driver) {
		this.driver = driver;
	}
	/**
	 * @return the route
	 */
	@ManyToOne
	@JoinColumn(name="routeId", insertable=true, updatable=true)
	public Route getRoute() {
		return route;
	}
	/**
	 * @param route the route to set
	 */
	public void setRoute(Route route) {
		this.route = route;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public double getSpeed() {
		return speed;
	}
	public void setSpeed(double speed) {
		this.speed = speed;
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
}
    

  

