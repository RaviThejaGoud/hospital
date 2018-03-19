package com.urt.persistence.model.study;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.churchgroup.util.date.DateFormatter;
import com.churchgroup.util.date.DateFunctions;
import com.urt.persistence.model.base.PersistentObject;

@Entity
@Table(name = "staffSyllabusPlanner")
public class StaffSyllabusPlanner  extends PersistentObject {
	
    private static final long serialVersionUID = 3832626162173359411L;
    
    private long staffId;
    private long studySubjectId;
    protected long custId;
    private long academicYearId;
    private Date startDate;
    private Date completedDate;
    private long studyClassId;
    protected String chapterName;
    protected String unitName;
    protected String topicName;
    private long periodsRequired;
    private long periodsTaken;
    
    
    public long getStudyClassId() {
		return studyClassId;
	}
	public void setStudyClassId(long studyClassId) {
		this.studyClassId = studyClassId;
	}
	public long getStaffId() {
		return staffId;
	}
	public void setStaffId(long staffId) {
		this.staffId = staffId;
	}
	
	/**
	 * @return the studyClassId
	 */
	public long getStudySubjectId() {
		return studySubjectId;
	}
	/**
	 * @param studyClassId the studyClassId to set
	 */
	public void setStudySubjectId(long studySubjectId) {
		this.studySubjectId = studySubjectId;
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

    
    public StaffSyllabusPlanner() {
        this.createdDate=DateFunctions.getTodayPlusNdays(0);
        this.lastUpdatedDate=DateFunctions.getTodayPlusNdays(0);
        this.lastAccessDate=DateFunctions.getTodayPlusNdays(0);
    }

    public StaffSyllabusPlanner(long id) {
        setId(id);
    }

	@Override
	public int compareTo(Object object) {
		// TODO Auto-generated method stub
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

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
        .append("id", this.getId()).toString();
	}
	@Transient
	public String getStartDateStr() {
		return DateFormatter.formatDate(DateFormatter.MM_DD_YYYY_PATTERN1, getStartDate());
	}
	@Transient
	public String getCompleteDateStr() {
		return DateFormatter.formatDate(DateFormatter.MM_DD_YYYY_PATTERN1, getCompletedDate());
	}
	public long getAcademicYearId() {
		return academicYearId;
	}
	public void setAcademicYearId(long academicYearId) {
		this.academicYearId = academicYearId;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getCompletedDate() {
		return completedDate;
	}
	public void setCompletedDate(Date completedDate) {
		this.completedDate = completedDate;
	}
	public String getChapterName() {
		return chapterName;
	}
	public void setChapterName(String chapterName) {
		this.chapterName = chapterName;
	}
	public String getTopicName() {
		return topicName;
	}
	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}
	public long getPeriodsRequired() {
		return periodsRequired;
	}
	public void setPeriodsRequired(long periodsRequired) {
		this.periodsRequired = periodsRequired;
	}
	public long getPeriodsTaken() {
		return periodsTaken;
	}
	public void setPeriodsTaken(long periodsTaken) {
		this.periodsTaken = periodsTaken;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
}
