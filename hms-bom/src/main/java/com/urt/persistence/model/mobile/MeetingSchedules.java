package com.urt.persistence.model.mobile;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.churchgroup.util.object.ObjectFunctions;
import com.urt.persistence.model.base.PersistentObject;
import com.urt.persistence.model.study.Staff;
import com.urt.persistence.model.study.StudyClass;

@Entity
@Table(name = "meetingSchedules")
public class MeetingSchedules extends PersistentObject  {
	
	/**
     * 
     */
    private static final long serialVersionUID = 1L;

        /**
	 * Default size for StringBuffer initialization
	 */
	private static final int STRING_BUFFER_SIZE = 1024;
	
	protected Long custId;
	private Date date; 
	private Staff staff;
	private int slotDuration;
	private int morningSlotCount;
	private int afternoonSlotCount;
	private StudyClass studyClass;
	private String description;
	private String isAuto;
	
	@Column(name = "isAuto", nullable = true, length = 1,columnDefinition="char(1) default 'N'")
	public String getIsAuto() {
		return isAuto;
	}
	public void setIsAuto(String isAuto) {
		this.isAuto = isAuto;
	}
	protected Set<MeetingSlots> meetingSlots;
    
	public void addMeetingSlots(MeetingSlots meetingSlots) {
		if (ObjectFunctions.isNullOrEmpty(getMeetingSlots())) 
			this.meetingSlots = new HashSet<MeetingSlots>();
		
		getMeetingSlots().add(meetingSlots);
	}
	/**
	 * @return the studentFeePaidDetails
	 */
	@OneToMany(cascade=CascadeType.ALL)
 	@JoinColumn(name="meetingScheduleId")
	public Set<MeetingSlots> getMeetingSlots() {
		return meetingSlots;
	}
	/**
	 * @param studentFeePaidDetails the studentFeePaidDetails to set
	 */
	public void setMeetingSlots(Set<MeetingSlots> meetingSlots) {
		this.meetingSlots = meetingSlots;
	}
	
	public Long getCustId() {
		return custId;
	}
	public void setCustId(Long custId) {
		this.custId = custId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	@OneToOne
	@JoinColumn(name="staffId")
	public Staff getStaff() {
		return staff;
	}
	public void setStaff(Staff staff) {
		this.staff = staff;
	}
	public int getSlotDuration() {
		return slotDuration;
	}
	public void setSlotDuration(int slotDuration) {
		this.slotDuration = slotDuration;
	}
	public int getMorningSlotCount() {
		return morningSlotCount;
	}
	public void setMorningSlotCount(int morningSlotCount) {
		this.morningSlotCount = morningSlotCount;
	}
	public int getAfternoonSlotCount() {
		return afternoonSlotCount;
	}
	public void setAfternoonSlotCount(int afternoonSlotCount) {
		this.afternoonSlotCount = afternoonSlotCount;
	}
	@OneToOne
	@JoinColumn(name="studyClassId")
	public StudyClass getStudyClass() {
		return studyClass;
	}
	public void setStudyClass(StudyClass studyClass) {
		this.studyClass = studyClass;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
