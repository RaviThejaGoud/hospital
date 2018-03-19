package com.urt.persistence.model.event;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.churchgroup.util.date.DateFormatter;
import com.churchgroup.util.date.DateFunctions;
import com.churchgroup.util.object.ObjectFunctions;
import com.churchgroup.util.string.StringFunctions;
import com.hyniva.sms.ws.vo.EventsVO;
import com.urt.persistence.model.base.PersistentObject;
import com.urt.persistence.model.common.AcademicYear;
import com.urt.persistence.model.study.StudyClass;
import com.urt.persistence.model.user.Role;


/*
 * @create new table recurringEvents.
 */
@Entity
@Table(name = "event")
public class Events  extends PersistentObject {
	
    private static final long serialVersionUID = 3832626162173359411L;
    
    protected long custId;
    protected long smsEventId;
    //protected long academicYearId;
    protected String eventDescription;
    protected String eventName;
    protected Date startDate;
    protected String startTime;
    protected Date endDate;
    protected String endTime;
    protected String status="N";
    protected AcademicYear academicYear;
    private Set<StudyClass> studyClass;
    
    List<EventsAlbum> eventsAlbum;
    private Set<Role> role;
    protected String eventFor="all"; // this variable having 3 event types, they are All - all school members, cs - class & section, s - staff
    //protected Address eventAddress;
    private String includeNonTeachingStaff;
    
    private String viewClassesStaff;
    private String eventAddress;
    
    private String staffEvent;  // This variable for select the staff, Having 3 subtypes, they are A- All staff (teaching & Non teaching), T- teaching staff, N - Non teaching staff
    
   
    
    @Column(name = "staffEvent", nullable = true, length = 1,columnDefinition="char(1) default 'A'")
    public String getStaffEvent() {
		return staffEvent;
	}

	public void setStaffEvent(String staffEvent) {
		this.staffEvent = staffEvent;
	}

	public String getEventAddress() {
		return eventAddress;
	}

	public void setEventAddress(String eventAddress) {
		this.eventAddress = eventAddress;
	}

	@Transient
    public String getViewClassesStaff() {
		
		if(!StringFunctions.isNullOrEmpty(this.getEventFor()))
		{
			if("all".equalsIgnoreCase(this.getEventFor()))
			{
				this.setViewClassesStaff("All"); 
			}
			else if("cs".equalsIgnoreCase(this.getEventFor()))
			{
				StringBuffer classStaff = new StringBuffer();
				if(!ObjectFunctions.isNullOrEmpty(this.getStudyClass()))
				{
					classStaff.append("Classes -");
					for(StudyClass studyClass : this.getStudyClass())
					{
						classStaff.append(studyClass.getClassAndSection()+",");
					}
				}
				
				if(!ObjectFunctions.isNullOrEmpty(this.getRole()))
				{
					classStaff.append("Non Teaching Staff -");
					for(Role role : this.getRole())
					{
						classStaff.append(role.getDescription()+",");
					}
				}
				
				this.setViewClassesStaff(classStaff.toString()); 
			}
			else if("s".equalsIgnoreCase(this.getEventFor()))
			{
				if("A".equalsIgnoreCase(this.getStaffEvent()))
					this.setViewClassesStaff("All Staff");
				else if("T".equalsIgnoreCase(this.getStaffEvent()))
					this.setViewClassesStaff("All Teaching Staff"); 
				else if("N".equalsIgnoreCase(this.getStaffEvent()))
					this.setViewClassesStaff("All Non Teaching Staff"); 
			}
		}
		else
			this.setViewClassesStaff("All"); 
		
		return viewClassesStaff;
	}

	public void setViewClassesStaff(String viewClassesStaff) {
		this.viewClassesStaff = viewClassesStaff;
	}

	@Column(name = "includeNonTeachingStaff", nullable = false, length = 1,columnDefinition="char(1) default 'N'")
    public String getIncludeNonTeachingStaff() {
		return includeNonTeachingStaff;
	}

	public void setIncludeNonTeachingStaff(String includeNonTeachingStaff) {
		this.includeNonTeachingStaff = includeNonTeachingStaff;
	}

	public String getEventFor() {
		return eventFor;
	}

	public void setEventFor(String eventFor) {
		this.eventFor = eventFor;
	}

	 /*@OneToOne
	 @JoinColumn(name="eventAddressId")
	public Address getEventAddress() {
		return eventAddress;
	}

	public void setEventAddress(Address eventAddress) {
		this.eventAddress = eventAddress;
	}*/

	@ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name = "EventRole",
       joinColumns = { @JoinColumn(name = "eventId") },
       inverseJoinColumns = { @JoinColumn(name = "roleId") })
    public Set<Role> getRole() {
		return role;
	}

	public void setRole(Set<Role> role) {
		this.role = role;
	}

	@Transient
    public List<EventsAlbum> getEventsAlbum() {
		return eventsAlbum;
	}

	public void setEventsAlbum(List<EventsAlbum> eventsAlbum) {
		this.eventsAlbum = eventsAlbum;
	}


	 @ManyToMany(fetch=FetchType.EAGER)
	    @JoinTable(name = "EventStudyClass",
	       joinColumns = { @JoinColumn(name = "eventId") },
	       inverseJoinColumns = { @JoinColumn(name = "studyClassId") })
	public Set<StudyClass> getStudyClass() {
		return studyClass;
	}

	public void setStudyClass(Set<StudyClass> studyClass) {
		this.studyClass = studyClass;
	}

	/**
     * @return the academicYear
     */
    @OneToOne
    @JoinColumn(name="academicYearId")
    public AcademicYear getAcademicYear() {
		return academicYear;
    }
    
    public void setAcademicYear(AcademicYear academicYear) {
		this.academicYear = academicYear;
    }
    public Events() {
    	
    }
   
    public Events(long id) {
        setId(id);
    }
    	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

	/**
	 * @return the endTime
	 */
	public String getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
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
	 * @return the custId
	 */
	public long getCustId() {
		return custId;
	}

	/**
	 * @param custId the custId to set
	 */
	public void setCustId(long custId) {
		this.custId = custId;
	}

	/**
	 * @return the smsEventId
	 */
	public long getSmsEventId() {
		return smsEventId;
	}

	/**
	 * @param smsEventId the smsEventId to set
	 */
	public void setSmsEventId(long smsEventId) {
		this.smsEventId = smsEventId;
	}

	/**
	 * @return the startTime
	 */
	public String getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the eventDescription
	 */
	public String getEventDescription() {
		return eventDescription;
	}

	/**
	 * @param eventDescription the eventDescription to set
	 */
	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
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
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", this.getId())
                .append("startDate",this.startDate)
                .append("endDate",this.endDate).toString();
        
    }

    /**
     * @see java.lang.Comparable#compareTo(Object)
     */
        @Override
        public int compareTo(Object object) {
            return 0;
        }
        public boolean equals(Object o) {
               return true;
        }

        public int hashCode() {
          return 0;
        }

		/**
		 * @return the startDate
		 */
		@Column(name = "startDate", nullable = true, length = 16)
		public Date getStartDate() {
			return startDate;
		}

		/**
		 * @param startDate the startDate to set
		 */
		public void setStartDate(Date startDate) {
			this.startDate = startDate;
		}

		/**
		 * @return the endDate
		 */
		@Column(name = "endDate", nullable = true, length = 16)
		public Date getEndDate() {
			return endDate;
		}

		/**
		 * @param endDate the endDate to set
		 */
		public void setEndDate(Date endDate) {
			this.endDate = endDate;
		}

		@Transient
		public String getEventStartDateStr() {
	        return DateFormatter.formatDate(DateFormatter.ddMMMyyyy_PATTERN1,this.getStartDate());
	    }
		@Transient
		public String getEventEndDateStr() {
	        return DateFormatter.formatDate(DateFormatter.ddMMMyyyy_PATTERN1,this.getEndDate());
	    }
		
		@Transient
		public String getEventStartDate() {
	        return DateFormatter.formatDate(DateFormatter.MMDDYYYY_PATTERN,this.getStartDate());
	    }
		@Transient
		public String getEventEndDate() {
	        return DateFormatter.formatDate(DateFormatter.MMDDYYYY_PATTERN,this.getEndDate());
	    }
		
		@Transient
		public String getStartDateStr() {
	        return DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_HHMMSS_PATTERN,this.getStartDate());
	    }
		@Transient
		public String getEndDateStr() {
	        return DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_HHMMSS_PATTERN,this.getEndDate());
	    }
		@Transient
		public String getCalendarEventEndDateStr() {
			
			return DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_HHMMSS_PATTERN, DateFunctions.add(this.getEndDate(), 1));
		}
		@Transient
		public void copyFrom(Events event)
		{
			this.endDate = event.endDate;
			this.endTime = event.endTime;
			this.eventDescription = event.eventDescription;
			this.eventName = event.eventName;
			this.smsEventId = event.smsEventId;
			this.startDate = event.startDate;
			this.startTime = event.startTime;
			this.custId = event.custId;
			this.academicYear = event.academicYear;
			this.status = event.status;
		}
		
		@Transient
		public void addStudyClass(StudyClass studyClass) {
			if(ObjectFunctions.isNullOrEmpty(studyClass)){
				studyClass=new StudyClass();
			}
			getStudyClass().add(studyClass);
		}
		
		
		@Transient
		public Events copyFromVoToEntity(Events event, EventsVO eventsVO)
		{
			if(event.getId() == 0)
				event.id = eventsVO.id;
			
			event.custId = eventsVO.custId;
			event.smsEventId = eventsVO.smsEventId;
			event.eventDescription = eventsVO.eventDescription;
			event.eventName = eventsVO.eventName;
			//event.startDate = DateFunctions.convertStringToDate(eventsVO.startDate);
			event.startTime = eventsVO.startTime;
			//event.endDate = DateFunctions.convertStringToDate(eventsVO.endDate);
			event.endTime = eventsVO.endTime;
			event.status = eventsVO.status;
			event.eventFor = eventsVO.eventFor;
			event.includeNonTeachingStaff = eventsVO.includeNonTeachingStaff;
			event.eventAddress = eventsVO.address;
			event.staffEvent = eventsVO.staffEvent;
			
			return event;
		}

		public EventsVO copyFromEntityToVo(Events event)
		{
			EventsVO eventsVO = new EventsVO();
			eventsVO.id = event.id;
			eventsVO.custId = event.custId;
			eventsVO.smsEventId = event.smsEventId;
			eventsVO.eventDescription = ObjectFunctions.isNullOrEmpty(event.getEventDescription())? "": event.getEventDescription();
			eventsVO.eventName = event.eventName;
			eventsVO.startDateTime = ObjectFunctions.isNullOrEmpty(event.startDate)? "": DateFunctions.convertDateToString(event.startDate);
			eventsVO.startTime = ObjectFunctions.isNullOrEmpty(event.getStartTime())? "": event.getStartTime();
			eventsVO.endDateTime = ObjectFunctions.isNullOrEmpty(event.endDate)? "": DateFunctions.convertDateToString(event.endDate);
			eventsVO.endTime = ObjectFunctions.isNullOrEmpty(event.getEndTime())? "": event.getEndTime();
			eventsVO.status = event.status;
			eventsVO.eventFor = event.eventFor;
			eventsVO.includeNonTeachingStaff = event.includeNonTeachingStaff;
			eventsVO.address = event.eventAddress;
			eventsVO.academicYearId = event.getAcademicYear().getId();
			eventsVO.staffEvent = event.staffEvent;
			eventsVO.eventCreatedUserId = event.getCreatedById();
			
			/*if(!ObjectFunctions.isNullOrEmpty(event.getEventAddress()))
			{
				Address eventsAddress = new Address();
				eventsVO.setEventAddressVO(eventsAddress.copyFromEntityToVo(event.getEventAddress()));
			}*/
			
			return eventsVO;
		}
		
		
		
}