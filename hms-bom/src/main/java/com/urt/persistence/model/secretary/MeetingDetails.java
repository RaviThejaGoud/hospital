package com.urt.persistence.model.secretary;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.churchgroup.util.object.ObjectFunctions;
import com.urt.persistence.model.base.PersistentObject;
import com.urt.persistence.model.common.Attachment;

@Entity
@Table(name = "meetingDetails")
public class MeetingDetails  extends PersistentObject {

	private static final long serialVersionUID = 3832626162173359411L;

	protected Date meetingDate;
	protected String meetingAgenda;
	protected String place;
	protected String comments;
	protected String startTime;
	protected long orgId;
	protected List<MeetingRequests> meetingRequests;
	protected List<MeetingRequestToCustomers> meetingRequestToCustomers;
	protected List<Attachment> attachment;
	
	
	
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="meetingDetailsId")
	public List<Attachment> getAttachment() {
		if(ObjectFunctions.isNullOrEmpty(attachment))
		{
			attachment = new ArrayList<Attachment>();
		}
		return attachment;
	}

	public void setAttachment(List<Attachment> attachment) {
		this.attachment = attachment;
	}

	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="meetingDetailsId")
	public List<MeetingRequestToCustomers> getMeetingRequestToCustomers() 
	{
		if(ObjectFunctions.isNullOrEmpty(meetingRequestToCustomers))
		{
			meetingRequestToCustomers = new ArrayList<MeetingRequestToCustomers>();
		}
		return meetingRequestToCustomers;
	}

	public void setMeetingRequestToCustomers(
			List<MeetingRequestToCustomers> meetingRequestToCustomers) {
		this.meetingRequestToCustomers = meetingRequestToCustomers;
	}

	public long getOrgId() {
		return orgId;
	}

	public void setOrgId(long orgId) {
		this.orgId = orgId;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="meetingDetailsId")	
	public List<MeetingRequests> getMeetingRequests() {
		if(ObjectFunctions.isNullOrEmpty(this.meetingRequests))
		{
	    	 this.meetingRequests=new ArrayList<MeetingRequests>();
	    }
		return meetingRequests;
	}

	public void setMeetingRequests(List<MeetingRequests> meetingRequests) {
		this.meetingRequests = meetingRequests;
	}
	
	public Date getMeetingDate() {
		return meetingDate;
	}
	public void setMeetingDate(Date meetingDate) {
		this.meetingDate = meetingDate;
	}
	public String getMeetingAgenda() {
		return meetingAgenda;
	}
	public void setMeetingAgenda(String meetingAgenda) {
		this.meetingAgenda = meetingAgenda;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	@Override
	public int compareTo(Object object) {
		return 0;
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
	public String toString() {
		return "";
	}
	
	@Transient
	public void addMeetingRequestToCustomers(MeetingRequestToCustomers meetingRequestToCustomers) {
		if(ObjectFunctions.isNullOrEmpty(meetingRequestToCustomers)){
			meetingRequestToCustomers=new MeetingRequestToCustomers();
		}
		getMeetingRequestToCustomers().add(meetingRequestToCustomers);
	}
	@Transient
	public void addAttachmentDetails(Attachment attachment) {
		if (ObjectFunctions.isNullOrEmpty(getAttachment())) {
			this.attachment = new ArrayList<Attachment>();
		}
		getAttachment().add(attachment);
	}
}
