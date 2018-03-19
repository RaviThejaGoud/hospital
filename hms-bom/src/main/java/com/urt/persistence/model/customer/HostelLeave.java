package com.urt.persistence.model.customer;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.struts2.json.annotations.JSON;

import com.churchgroup.util.date.DateFormatter;
import com.churchgroup.util.date.DateFunctions;
import com.churchgroup.util.object.ObjectFunctions;
import com.urt.persistence.model.base.PersistentObject;
import com.urt.persistence.model.common.AcademicYear;
import com.urt.persistence.model.user.User;

@Entity
@Table(name = "hostelLeaves")
public class HostelLeave  extends PersistentObject {
	
    private static final long serialVersionUID = 3832626162173359411L;
    
    protected User user;   
    //protected String subject;  
	protected String description;
    /*protected double days;*/
    private long custId;
	protected Date startDate;
	protected Date endDate;
	protected String leaveStatus="P";
	protected String leaveType;
	protected String startTime;
	protected String endTime;
	protected long supervisorId;
	protected long childRollNo;
	protected long childAccountId;
	protected String approvalsComment;
	protected AcademicYear academicYear;
	private double leavesCount;
	
    
    
	@Column(name = "leavesCount", nullable = false ,columnDefinition="double default 0")
	public double getLeavesCount() {
		return leavesCount;
	}
	public void setLeavesCount(double leavesCount) {
		this.leavesCount = leavesCount;
	}
	/**
	 * @return the academicYear
	 */
	@OneToOne
	@JoinColumn(name="academicYearId", insertable=true, updatable=true)
	public AcademicYear getAcademicYear() {
		return academicYear;
	}
	public void setAcademicYear(AcademicYear academicYear) {
		this.academicYear = academicYear;
	}
		
	@Column(name = "approvalsComment", nullable = true, length = 2048)
	public String getApprovalsComment() {
		return this.approvalsComment;
	}
	public void setApprovalsComment(String approvalsComment) {
		this.approvalsComment = approvalsComment;
	}
	@Transient	
	public long getChildAccountId() {
		return this.childAccountId;
	}
	@Transient	
	public void setChildAccountId(long childAccountId) {
		this.childAccountId = childAccountId;
	}
	@Transient	
	public long getChildRollNo() {
		return this.childRollNo;
	}
	@Transient
	public void setChildRollNo(long childRollNo) {
		this.childRollNo = childRollNo;
	}

    
	/**
	 * @return the supervisorId
	 */
	public long getSupervisorId() {
		return supervisorId;
	}
	/**
	 * @param supervisorId the supervisorId to set
	 */
	public void setSupervisorId(long supervisorId) {
		this.supervisorId = supervisorId;
	}
	public String getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}

	/*@Column(name = "subject", nullable = true, length = 250)
    public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}*/
	@Column(name = "leaveStatus", nullable = true, length = 10)
	public String getLeaveStatus() {
		return leaveStatus;
	}

	public void setLeaveStatus(String leaveStatus) {
		this.leaveStatus = leaveStatus;
	}

	@JSON(serialize=false)
	public Date getStartDate() {
		return startDate;
	}

	@JSON(serialize=false)
	public Date getEndDate() {
		return endDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getStartTime() {
		return startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public HostelLeave() {
        
    }

    public HostelLeave(long id) {
        setId(id);
    }
    @Override
    public int compareTo(Object object) 
    {
    	Leave target = (Leave) object;
    	long timeDifference = 0;
    	if (target.getCreatedDate() != null && this.getCreatedDate() != null) {
	    	timeDifference = this.getCreatedDate().getTime()
	    	- target.getCreatedDate().getTime();
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
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        final Leave leave = (Leave) o;

        if (description != null ? !description.equals(leave.getDescription()) : leave.getDescription() != null) return false;

        return true;
    }

	/**
	 * @return the account
	 */
    @ManyToOne
    @JoinColumn(name = "accountId", referencedColumnName = "id")
	public User getUser() {
		return user;
	}

	/**
	 * @param account the account to set
	 */
	public void setUser(User user) {
		this.user = user;
	}


	/**
	 * @return the description
	 */
	@Column(name = "description", nullable = true, length = 2048)
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

/*	*//**
	 * @return the days
	 *//*
	@Column(name = "days", nullable = false, length = 10)
	public double getDays() {
		return days;
	}

	*//**
	 * @param days the days to set
	 *//*
	public void setDays(double days) {
		this.days = days;
	}*/

	@Override
	public int hashCode() {
        return (getStrId() != null ? this.getStrId().hashCode() : 0);
    }

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
        .append("id", this.getId())
        .append("desc", this.description)
        .toString();
	}

	public long getCustId() {
		return custId;
	}


	/**
	 * @param custId the custId to set
	 */
	public void setCustId(long custId) {
		this.custId = custId;
	} 
	@Transient
    public String getStartDateStr() {
        return DateFormatter.formatDate(DateFormatter.MMDDCCYY_PATTERN,this.startDate);

    }
	@Transient
    public String getEndDateStr() {
        return DateFormatter.formatDate(DateFormatter.MMDDCCYY_PATTERN,this.endDate);

    }
	@Transient
	public String getUserFormattedStartDate() {
		return DateFormatter.formatDate(DateFormatter.ddMMMyyyy_PATTERN1, this.startDate);
	}
	
	@Transient
	public String getUserFormattedEndDate() {
		return DateFormatter.formatDate(DateFormatter.ddMMMyyyy_PATTERN1, this.endDate);
	}
	
	@Transient
    public boolean getCompareTwoDates() {
        SimpleDateFormat fm = new SimpleDateFormat("MM/dd/yyyy");
        Calendar c1 = GregorianCalendar.getInstance();
        Calendar c2 = GregorianCalendar.getInstance();
        String aStrDate = fm.format(getStartDate());
        String aStrSecondDate = fm.format(new Date());
        c1.setTime(DateFormatter.parseString("MM/dd/yyyy",aStrDate));
        c1.set(c1.HOUR, 0);
        c1.set(c1.MINUTE, 0);
        c1.set(c1.SECOND, 0);
        c2.setTime(DateFormatter.parseString("MM/dd/yyyy",aStrSecondDate));
        c2.set(c1.HOUR, 0);
        c2.set(c1.MINUTE, 0);
        c2.set(c1.SECOND, 0);
        log.debug(c1.getTime());
        log.debug(c2.getTime());
        if(c1.after(c2) || c1.compareTo(c2) == 0){
            return true;
        }else if(c1.before(c2)){
            return false;
        }else if(c1.equals(c2)){
            return true;
        }
        return false;
    }
/*	@Transient
    public String getFormattedEndDate() {
        return DateFormatter.formatDate(DateFormatter.MMDDCCYY_PATTERN,this.endDate);

    }*/
	@Transient
    public String getLeaveStatusDesc() {
		if("CL".equalsIgnoreCase(getLeaveType()))
    	{
			return "Casual Leave";
    	}
		else if("SL".equalsIgnoreCase(getLeaveType()))
		{
			return "Sick Leave";
		}else if("EL".equalsIgnoreCase(getLeaveType()))
		{
			return "Earned Leave";
		}else if("PL".equalsIgnoreCase(getLeaveType()))
			return "Pay Leave";
		else
			return "";
    }
	@Transient
	public long getAcademicYearId() {
		if(ObjectFunctions.isNullOrEmpty(this.academicYear))
		{
			return 0;
		}
		return this.academicYear.getId();
	}
	@Transient
	public long getAccountId() {
		if(ObjectFunctions.isNullOrEmpty(this.user))
		{
			return 0;
		}
		return this.user.getId();
	}
	@Transient
	public int getNoofDays(){
		return DateFunctions.daysBetween(this.getStartDate(), this.getEndDate())+1;
	}
	
	@Transient
	public HostelLeave copyFromLeaveObj(Leave leave, HostelLeave hostelLeave)
	{
		this.leaveType = leave.getLeaveType();
		this.leaveStatus = leave.getLeaveStatus();
		this.leavesCount = leave.getLeavesCount();
		this.startDate = leave.getStartDate();
		this.endDate = leave.getEndDate();
		this.description = leave.getDescription();
		this.custId = leave.getCustId();
		this.academicYear = leave.getAcademicYear();
		this.user = leave.getUser();
		this.supervisorId = leave.getSupervisorId();
		this.createdDate = leave.getCreatedDate();
		this.lastAccessDate = leave.getLastAccessDate();
		this.lastUpdatedDate = leave.getLastUpdatedDate();
		
		return this;
		
	}
}
    

  

