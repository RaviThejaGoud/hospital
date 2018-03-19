package com.urt.persistence.model.mobile;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.urt.persistence.model.base.PersistentObject;
import com.urt.persistence.model.user.User;

@Entity
@Table(name = "meetingSlots")
public class MeetingSlots extends PersistentObject  {
	
	/**
     * 
     */
    private static final long serialVersionUID = 1L;

        /**
	 * Default size for StringBuffer initialization
	 */
	private static final int STRING_BUFFER_SIZE = 1024;
	
	protected Long custId;
	private String slotTime;
	private String slotType;
	private Long meetingScheduleId;
	
	@Column(name = "slotType", nullable = true, length = 1,columnDefinition="char(1) default 'M'")
	public String getSlotType() {
		return slotType;
	}
	public void setSlotType(String slotType) {
		this.slotType = slotType;
	}
	public Long getMeetingScheduleId() {
		return meetingScheduleId;
	}
	public void setMeetingScheduleId(Long meetingScheduleId) {
		this.meetingScheduleId = meetingScheduleId;
	}
	public Long getCustId() {
		return custId;
	}
	public void setCustId(Long custId) {
		this.custId = custId;
	}
	public String getSlotTime() {
		return slotTime;
	}
	public void setSlotTime(String slotTime) {
		this.slotTime = slotTime;
	}
	@OneToOne
	@JoinColumn(name="accountId")
	public User getAccountId() {
		return accountId;
	}
	public void setAccountId(User accountId) {
		this.accountId = accountId;
	}
	private User accountId;

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
