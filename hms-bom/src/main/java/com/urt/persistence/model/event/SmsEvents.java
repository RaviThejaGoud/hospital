package com.urt.persistence.model.event;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.churchgroup.util.date.DateFormatter;
import com.urt.persistence.model.base.PersistentObject;

/*
 * @create new table eventDetails.
 */
@Entity
@Table(name = "smsEvents")
public class SmsEvents extends PersistentObject {

	private static final long serialVersionUID = 3832626162173359411L;

	private String title;
	private String eventName;
	private String status="N";
	private Date startDate;
	private Date endDate;
	protected String academicYear;

	public SmsEvents() {

	}

	public SmsEvents(long id) {
		setId(id);
	}
	
	
	/**
	 * @return the eventName
	 */
	public String getEventName() {
		return eventName;
	}

	/**
	 * @param eventName the eventName to set
	 */
	public void setEventName(String eventName) {
		this.eventName = eventName;
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
	 * @return the academicYear
	 */
	public String getAcademicYear() {
		return academicYear;
	}

	/**
	 * @param academicYear the academicYear to set
	 */
	public void setAcademicYear(String academicYear) {
		this.academicYear = academicYear;
	}

	/**
	 * @return Returns the title of the event
	 */
	@Column(name = "title", nullable = true, length = 128, unique = false)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return Returns the startDate.
	 */
	@Column(name = "startDate", nullable = true, length = 12, unique = false)
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return Returns the endDate.
	 */
	@Column(name = "endDate", nullable = true, length = 12, unique = false)
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * @see java.lang.Comparable#compareTo(Object)
	 */
	@Override
	public int compareTo(Object object) {
		SmsEvents target = (SmsEvents) object;
		long timeDifference = 0;
		if (target.getStartDate() != null && this.getStartDate() != null) {
			timeDifference = this.getStartDate().getTime()
					- target.getStartDate().getTime();
		}
		int difference;
		if (timeDifference == 0) {
			difference = 0;
		} else if (timeDifference < 0) {
			difference = -1;
		} else {
			difference = 1;
		}
		return difference;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof SmsEvents))
			return false;

		final SmsEvents clEvent = (SmsEvents) o;

		if (getStrId() != null ? !getStrId().equals(clEvent.getStrId())
				: clEvent.getStrId() != null)
			return false;

		return true;
	}

	public int hashCode() {
		return (title != null ? title.hashCode() : 0);
	}

	@Transient
	public String getEventEndDateStr() {
		try {
			return DateFormatter.formatDate(DateFormatter.MM_DD_YYYY_PATTERN1 ,
					this.getEndDate());
		} catch (Exception ex) {
			return "";
		}
	}
	
	@Transient
	public String getEventStartDateStr() {
		try {
			return DateFormatter.formatDate(DateFormatter.MM_DD_YYYY_PATTERN1 ,
					this.getStartDate());
		} catch (Exception ex) {
			return "";
		}
	}

	public void copyFrom(SmsEvents event) {
		this.title = event.getTitle();
		this.startDate = event.getStartDate();
		this.endDate = event.getEndDate();
		// this.custId=event.getCustId();
		// this.recType=event.getRecType();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "";
	}
}