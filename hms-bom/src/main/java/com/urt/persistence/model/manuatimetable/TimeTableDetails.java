package com.urt.persistence.model.manuatimetable;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.urt.persistence.model.base.PersistentObject;
/**
 * @author JR
 *
 */
@Entity
@Table(name = "timeTableDetails")
public class TimeTableDetails  extends PersistentObject {

	private long dayId;
    private long timeTablePeriodId;
    private long studyClassId;
    private long studySubjectId;
    private Date fromDate;
    private Date toDate;
    private boolean status;
    

    @Column(name = "studySubjectId",nullable = false, columnDefinition=" int default 0")
    public long getStudySubjectId() {
		return studySubjectId;
	}

	public void setStudySubjectId(long studySubjectId) {
		this.studySubjectId = studySubjectId;
	}
    
    @Column(name = "studyClassId",nullable = false, columnDefinition=" int default 0")
	public long getStudyClassId() {
		return studyClassId;
	}

	public void setStudyClassId(long studyClassId) {
		this.studyClassId = studyClassId;
	}
	@Column(name = "dayId",nullable = false, columnDefinition=" int default 0")
	public long getDayId() {
		return dayId;
	}

	public void setDayId(long dayId) {
		this.dayId = dayId;
	}
	
	@Column(name = "timeTablePeriodId",nullable = false, columnDefinition=" int default 0")
	public long getTimeTablePeriodId() {
		return timeTablePeriodId;
	}

	public void setTimeTablePeriodId(long timeTablePeriodId) {
		this.timeTablePeriodId = timeTablePeriodId;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	@Column(name = "status", nullable = true, length = 1,columnDefinition="char(1) default 'Y'")
	@Type(type = "yes_no")
	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
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