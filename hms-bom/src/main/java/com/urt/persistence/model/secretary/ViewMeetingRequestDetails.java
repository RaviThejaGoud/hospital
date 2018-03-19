package com.urt.persistence.model.secretary;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.churchgroup.util.date.DateFormatter;

/*
 * @create new table customer.
 */
@Entity
@Table(name = "vw_meetingRequestDetails")
public class ViewMeetingRequestDetails implements Serializable,Cloneable,Comparable  {

    private static final long serialVersionUID = 1L;
    
    
    private long meetingDetailsId;
    private long orgId;
    private long custId;
    private long meetingRequestToCustomersId;
    private Date meetingDate;
    protected String organization;
    protected String place;
    protected String startTime;
    protected String meetingAgenda;
    protected String comments;
    
    
    
    
    public long getMeetingDetailsId() {
		return meetingDetailsId;
	}

	public void setMeetingDetailsId(long meetingDetailsId) {
		this.meetingDetailsId = meetingDetailsId;
	}

	public long getOrgId() {
		return orgId;
	}

	public void setOrgId(long orgId) {
		this.orgId = orgId;
	}

	public long getCustId() {
		return custId;
	}

	public void setCustId(long custId) {
		this.custId = custId;
	}

	@Id
	public long getMeetingRequestToCustomersId() {
		return meetingRequestToCustomersId;
	}

	public void setMeetingRequestToCustomersId(long meetingRequestToCustomersId) {
		this.meetingRequestToCustomersId = meetingRequestToCustomersId;
	}

	public Date getMeetingDate() {
		return meetingDate;
	}

	public void setMeetingDate(Date meetingDate) {
		this.meetingDate = meetingDate;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getMeetingAgenda() {
		return meetingAgenda;
	}

	public void setMeetingAgenda(String meetingAgenda) {
		this.meetingAgenda = meetingAgenda;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public ViewMeetingRequestDetails() {

    }

   /* *//**
	 * @return the bedList
	 *//*
	@OneToMany(fetch=FetchType.EAGER)
	@JoinColumn(name="roomId",referencedColumnName="roomId") 
	public Set<Bed> getBedList() {
	    return bedList;
	}

	*//**
	 * @param bedList the bedList to set
	 *//*
	public void setBedList(Set<Bed> bedList) {
	    this.bedList = bedList;
	}*/
    
    /**
     * @return the customer name.
     */

    @Override
    public String toString() {
	return "";
    }

    @Override
	public int compareTo(Object object) {
	ViewMeetingRequestDetails target = (ViewMeetingRequestDetails) object;
    	
        return 0;
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
    @Transient
    public String getMeetingDateTime() {
	return getUserFormattedmeetingDate()+" - "+getStartTime();
    }
    @Transient
	public String getUserFormattedmeetingDate() {
		return DateFormatter.formatDate(DateFormatter.ddMMMyyyy_PATTERN1, this.meetingDate);
	}
	
	@Transient
    public String getmeetingDateStr() {
        return DateFormatter.formatDate(DateFormatter.MMDDCCYY_PATTERN,this.meetingDate);
    }
}
