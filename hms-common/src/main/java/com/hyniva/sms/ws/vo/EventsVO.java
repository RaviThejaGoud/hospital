package com.hyniva.sms.ws.vo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import com.churchgroup.util.date.DateFormatter;
import com.hyniva.sms.ws.vo.base.SMSBaseVO;


public class EventsVO extends SMSBaseVO{
	
	public long id;
    public String eventDescription;
    public String eventName;
   // public String startDateStr;
    public String startTime;
   // public String endDateStr;	
    public String endTime;
    public String eventFor;
    public long academicYearId;
   // public AddressVO eventAddressVO;
    public long custId;
    public long smsEventId;
    public String status;
    
    public String startDateTime;
    public String endDateTime;
    public long eventCreatedUserId;
    public String studyClassIds;
    public String includeNonTeachingStaff;
    
    public String nonTeachingRoleIds;
    public String address;
    protected Set<AlbumAttachmentVO> albumAttachmentVo;
    public String requestType;
    public String staffEvent;
   
    
    

	public String getStaffEvent() {
		return staffEvent;
	}

	public void setStaffEvent(String staffEvent) {
		this.staffEvent = staffEvent;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public String getStartDateTime() {
		return startDateTime;
	}

	public void setStartDateTime(String startDateTime) {
		this.startDateTime = startDateTime;
	}

	public String getEndDateTime() {
		return endDateTime;
	}

	public void setEndDateTime(String endDateTime) {
		this.endDateTime = endDateTime;
	}

	public Set<AlbumAttachmentVO> getAlbumAttachmentVo() {
    	
    	if(albumAttachmentVo == null)
        {
    		albumAttachmentVo = new HashSet<AlbumAttachmentVO>();
        }
    	
		return albumAttachmentVo;
	}

	public void setAlbumAttachmentVo(Set<AlbumAttachmentVO> albumAttachmentVo) {
		this.albumAttachmentVo = albumAttachmentVo;
	}

	    
	public String getNonTeachingRoleIds() {
		return nonTeachingRoleIds;
	}
	public void setNonTeachingRoleIds(String nonTeachingRoleIds) {
		this.nonTeachingRoleIds = nonTeachingRoleIds;
	}
	public String getIncludeNonTeachingStaff() {
		return includeNonTeachingStaff;
	}
	public void setIncludeNonTeachingStaff(String includeNonTeachingStaff) {
		this.includeNonTeachingStaff = includeNonTeachingStaff;
	}
	public String getStudyClassIds() {
		return studyClassIds;
	}
	public void setStudyClassIds(String studyClassIds) {
		this.studyClassIds = studyClassIds;
	}
	public long getEventCreatedUserId() {
		return eventCreatedUserId;
	}
	public void setEventCreatedUserId(long eventCreatedUserId) {
		this.eventCreatedUserId = eventCreatedUserId;
	}
	public long getSmsEventId() {
		return smsEventId;
	}
	public void setSmsEventId(long smsEventId) {
		this.smsEventId = smsEventId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public long getAcademicYearId() {
		return academicYearId;
	}
	public void setAcademicYearId(long academicYearId) {
		this.academicYearId = academicYearId;
	}
	public long getCustId() {
		return custId;
	}
	public void setCustId(long custId) {
		this.custId = custId;
	}
	public String getEventFor() {
		return eventFor;
	}
	public void setEventFor(String eventFor) {
		this.eventFor = eventFor;
	}
	
	/*public AddressVO getEventAddressVO() {
		return eventAddressVO;
	}
	public void setEventAddressVO(AddressVO eventAddressVO) {
		this.eventAddressVO = eventAddressVO;
	}*/
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getEventDescription() {
		return eventDescription;
	}
	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	 
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	
	public String getEventStartDate() {
       //return DateFormatter.formatDate(DateFormatter.MMDDYYYY_PATTERN,this.getStartDateTime());
		return convertDateStringFormat(this.getStartDateTime(), "yyyy-MM-dd HH:mm:ss", DateFormatter.MMDDYYYY_PATTERN);
    }
	public String getEventEndDate() {
       // return DateFormatter.formatDate(DateFormatter.MMDDYYYY_PATTERN,this.getEndDateTime());
		return convertDateStringFormat(this.getEndDateTime(), "yyyy-MM-dd HH:mm:ss", DateFormatter.MMDDYYYY_PATTERN);
    }
	
	public String convertDateStringFormat(String strDate, String fromFormat, String toFormat){
	       try{
	    	   SimpleDateFormat dateFormat2 = new SimpleDateFormat(toFormat.trim());
	    	   DateFormat formatter = new SimpleDateFormat(fromFormat.trim(), Locale.US);
	    	   Date date = formatter.parse(strDate); 
	    	   return dateFormat2.format(date);
	    	   
	       }catch (Exception e) {
	          e.printStackTrace();
	          return "";
	       }
	}
	
}
