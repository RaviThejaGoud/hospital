package com.urt.persistence.model.study;

/********************************************************************
 * Copyright (C) 2005-06

 * IFS
 * All Rights Reserved 
 *
 * File: Person.java
 ********************************************************************
 *
 *  Ver   Date              Student               Description
 *  ====  ========          ============       ==================
 *  3.0  Jul 9, 2006           Sreeram J           Created
 / ********************************************************************/
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.churchgroup.util.date.DateFormatter;
import com.churchgroup.util.date.DateFunctions;
import com.urt.persistence.model.base.PersistentObject;
import com.urt.persistence.model.common.AcademicYear;
import com.urt.util.excel.parser.ExcelField;
import com.urt.util.excel.parser.ExcelObject;
import com.urt.util.excel.parser.ParseType;

/**
 * Represents a person's full name
 */
/**
 * @author ubuntu-200
 *
 */
@Entity
@Table(name = "schoolHolidays")
@ExcelObject(parseType = ParseType.ROW, start = 3, end = 11)
public class SchoolHolidays extends PersistentObject {
	/**
     * 
     */
	
	private static final long serialVersionUID = 1L;

	/**
	 * Brief Description: A formal appellation attached to the name of a person
	 * or family by virtue of office, rank, hereditary privilege, noble birth,
	 * or attainment or used as a mark of respect. Pre-fill: True Pre-fill
	 * Source:
	 */
	
	@ExcelField(position = 3)
	private Date startDate;
	private long custId;
	@ExcelField(position = 4)
	private Date endDate;
	private String status;
	@ExcelField(position = 2)
	private String description;
	protected String startTime;
    protected String endTime;
    protected int noOfDays;
    private String holidayDate;
    private int monthId;
    private String yearId;
    protected AcademicYear academicYear;
    private long classId;
    //private String classHolidayDescription;
    
    @Transient
    @ExcelField(position = 5)
    private String schoolWorkingDay;
    
    
    
    @Transient
    public String getSchoolWorkingDay() {
		return schoolWorkingDay;
	}
	/*public void setSchoolWorkingDay(String schoolWorkingDay) {
		this.schoolWorkingDay = schoolWorkingDay;
	}
	public String getClassHolidayDescription() {
		return classHolidayDescription;
	}
	public void setClassHolidayDescription(String classHolidayDescription) {
		this.classHolidayDescription = classHolidayDescription;
	}*/
	@Transient
    @ExcelField(position = 1)
	private String schoolHolidayIdStr;
   
    public String getSchoolHolidayIdStr() {
		return schoolHolidayIdStr;
	}
	public void setSchoolHolidayIdStr(String schoolHolidayIdStr) {
		this.schoolHolidayIdStr = schoolHolidayIdStr;
	}
	@Column(name = "classId", nullable = true, columnDefinition="bigint(20) default 0")
    public long getClassId() {
		return classId;
	}
	public void setClassId(long classId) {
		this.classId = classId;
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
	
    @Column(name = "startTime", nullable = true, length = 10,unique=false)
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
	 * @return the endTime
	 */
	@Column(name = "endTime", nullable = true, length = 10,unique=false)
	public String getEndTime() {
		return endTime;
	}
	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
/*	public long getSchoolOrgId() {
		return schoolOrgId;
	}

	public void setSchoolOrgId(long schoolOrgId) {
		this.schoolOrgId = schoolOrgId;
	}*/
	/**
	 * @return the eventType
	 */
	
	public SchoolHolidays() {
		super();
	}
	public SchoolHolidays(Date startDate, Date endDate, String status,
			String description) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
		this.description = description;
	}
	
	/*@Column(name = "classNames", nullable = true, length = 1024)
	public String getClassNames() {
		return classNames;
	}
	public void setClassNames(String classNames) {
		this.classNames = classNames;
	}*/
	public Date getStartDate() {
		return startDate;
	}
	
	public long getCustId() {
		return custId;
	}
	public void setCustId(long custId) {
		this.custId = custId;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getNoOfDays() {
		return noOfDays;
	}

	public void setNoOfDays(int noOfDays) {
		this.noOfDays = noOfDays;
	}

	public String getHolidayDate() {
		return holidayDate;
	}

	public void setHolidayDate(String holidayDate) {
		this.holidayDate = holidayDate;
	}

	public int getMonthId() {
		return monthId;
	}

	public void setMonthId(int monthId) {
		this.monthId = monthId;
	}

	public String getYearId() {
		return yearId;
	}

	public void setYearId(String yearId) {
		this.yearId = yearId;
	}

	@Override
	public int compareTo(Object object) {
		SchoolHolidays target = (SchoolHolidays) object;
		long timeDifference = 0;
        if (target.getStartDate() != null && this.getStartDate() != null)
        {
        	timeDifference = this.getStartDate().getTime() - target.getStartDate().getTime();
        }
        int difference;
    	if (timeDifference == 0) {
    	difference = 0;
    	} else if (timeDifference > 0) {
    	difference = -1;
    	} else {
    	difference = 1;
    	}
    	return difference;
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
        .append("id", this.getId())
        .toString();
  
	}
	
	@Transient
	public String getStartDateStr() {
		return DateFormatter.formatDate(DateFormatter.MM_DD_YYYY_PATTERN1, this.getStartDate());
	}
	
	@Transient
	public String getEndDateStr() {
		return DateFormatter.formatDate(DateFormatter.MM_DD_YYYY_PATTERN1, this.getEndDate());
	}
	
	@Transient
	public String getHolidayStartDate() {
		return DateFormatter.formatDate(DateFormatter.MM_DD_YYYY_PATTERN1, this.getStartDate());
	}
	
	@Transient
	public String getHolidayEndDate() {
		return DateFormatter.formatDate(DateFormatter.MM_DD_YYYY_PATTERN1, this.getEndDate());
	}
	
	@Transient
	public String getStartDateFormat() {
		return DateFormatter.formatDate(DateFormatter.ddMMMyyyy_PATTERN1, this.getStartDate());
	}
	
	@Transient
	public String getEndDateFormat() {
		return DateFormatter.formatDate(DateFormatter.ddMMMyyyy_PATTERN1, this.getEndDate());
	}	
	
	/*this format used in sorting order holidays in view*/
	@Transient
    public String getSortingDateFormat() {
	return DateFormatter.formatDate(DateFormatter.MMDDYYYY_PATTERN, this.getStartDate());

    }
	 
	@Transient
	public String getHolidayDateStr() {
		return DateFormatter.formatDate(DateFormatter.MM_DD_YYYY_PATTERN1, DateFormatter.parseString(DateFormatter.CCYY_MM_DD_PATTERN, this.getHolidayDate()));
	}
	@Transient
	public String getCalendarStartDateStr() {
		return DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_HHMMSS_PATTERN, this.getStartDate());
	}
	
	@Transient
	public String getCalendarEndDateStr() {
		
		return DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_HHMMSS_PATTERN, DateFunctions.add(this.getEndDate(), 1));
		//return DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_HHMMSS_PATTERN, this.getEndDate());
	}
	@Transient
	public String getCalendarHolidayDateStr() {
		return DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_HHMMSS_PATTERN, this.getHolidayDate());
	}
	@Transient
	public String getStartDateFormate() {
		return DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_HHMMSS_PATTERN, this.getStartDate());
	}
	
	@Transient
	public String getEndDateFormate() {
		return DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_HHMMSS_PATTERN, this.getEndDate());
	}
	
	@Transient
	public int getNoOfHolidayDays() {
		return DateFunctions.daysBetween(this.getStartDate(), this.getEndDate())+1;
	}
}
