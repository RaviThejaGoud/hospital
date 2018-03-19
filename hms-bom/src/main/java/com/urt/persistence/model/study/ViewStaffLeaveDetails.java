package com.urt.persistence.model.study;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.CompareToBuilder;

import com.churchgroup.util.date.DateFormatter;
import com.churchgroup.util.date.DateFunctions;
import com.churchgroup.util.string.StringFunctions;


/********************************************************************
 * Copyright (C) 2005-06
 * IFS
 * All Rights Reserved 
 *
  * File: ViewGroupType.java
 ********************************************************************
 *
 *  Ver   Date              Name               Description
 *  ====  ========          ============       ==================
 *  1.0    Oct 05, 2010     Narahari          	Created
/********************************************************************/

@Entity
@Table(name = "vw_staffLeaveDetails")
public class ViewStaffLeaveDetails  implements Serializable,Cloneable,Comparable {


    /**
     * Default buffer size to be allocated for StringBuffer.
     */
    private static final int DEFAULT_BUFFER_SIZE = 1024;
     /**
     * 
     */
     private static final long serialVersionUID = 1L;
     
     private long accountId;
     private long custId;
     private String username;
     private String academicYearId;
     private String status;
     private long staffId;
     /*private long totalLeaves;*/
     private String firstName;
     private String lastName;
    // private String lastUpdatedBy;
    // private Date lastUpdatedDate;
     //private Date createdDate;
     /*private long casualLeaves;
     private long sickLeaves;*/
     private Date startDate;
     private Date endDate;
     private String leaveStatus;
     private String leaveType;
     private long leavesId;
     private String description;
     /*private double days;*/
     private String startTime;
     private String endTime;
     private long roleId;
     private String roleName;
     private String qualification1;
     private String qualification2;
     private String roleDescription;
     private String supervisorId;
     private long leaveSupervisorId;
     private String dateOfJoining;
     private String summary;
     private double totalLeavesTaken;
     private long leaveManagementId;
     private long userSickLeaves;
     private long userCasualLeaves;
     private double leavesCount;
     private String permanentOrContract;
     private long userEarnedLeaves;
     
 	/**
	 * @return the leavesCount
	 */
	public double getLeavesCount() {
		return leavesCount;
	}
	/**
	 * @param leavesCount the leavesCount to set
	 */
	public void setLeavesCount(double leavesCount) {
		this.leavesCount = leavesCount;
	}
	public long getLeaveManagementId() {
 		return leaveManagementId;
 	}
 	public void setLeaveManagementId(long leaveManagementId) {
 		this.leaveManagementId = leaveManagementId;
 	}

 	public long getUserSickLeaves() {
 		return userSickLeaves;
 	}
 	public void setUserSickLeaves(long userSickLeaves) {
 		this.userSickLeaves = userSickLeaves;
 	}

 	public long getUserCasualLeaves() {
 		return userCasualLeaves;
 	}
 	public void setUserCasualLeaves(long userCasualLeaves) {
 		this.userCasualLeaves = userCasualLeaves;
 	}
 	
     /**
	 * @return the summary
	 */
     @Transient
     public String getSummary() {
		return summary;
     }
	/**
	 * @param summary the summary to set
	 */
	public void setSummary(String summary) {
		this.summary = summary;
	}
	@Transient
     public double getTotalLeavesTaken() {
 		return totalLeavesTaken;
 	}
 	public void setTotalLeavesTaken(double totalLeavesTaken) {
 		this.totalLeavesTaken = totalLeavesTaken;
 	}
    public String getDateOfJoining() {
		return dateOfJoining;
	}
	public void setDateOfJoining(String dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}
 	
	/**
	 * @return the leaveSupervisorId
	 */
	public long getLeaveSupervisorId() {
		return leaveSupervisorId;
	}
	/**
	 * @param leaveSupervisorId the leaveSupervisorId to set
	 */
	public void setLeaveSupervisorId(long leaveSupervisorId) {
		this.leaveSupervisorId = leaveSupervisorId;
	}
	public String getSupervisorId() {
		return supervisorId;
	}
	public void setSupervisorId(String supervisorId) {
		this.supervisorId = supervisorId;
	}
     
     
     
	public ViewStaffLeaveDetails()
    {
        super();
    }
   
	public ViewStaffLeaveDetails(long accountId, long custId, String username,
			String academicYearId,
			String status, String qualification, long staffId,
			String firstName, String lastName,String middleName,
			Date startDate, Date endDate, String leaveStatus, String leaveType,
			long leavesId, String description) {
		super();
		this.accountId = accountId;
		this.custId = custId;
		this.username = username;
		this.academicYearId = academicYearId;
		this.status = status;
		this.qualification1 = qualification;
		this.staffId = staffId;
		/*this.totalLeaves = totalLeaves;*/
		this.firstName = firstName;
		this.lastName = lastName;
		//this.lastUpdatedBy = lastUpdatedBy;
		//this.lastUpdatedDate = lastUpdatedDate;
		//this.createdDate = createdDate;
		/*this.casualLeaves = casualLeaves;
		this.sickLeaves = sickLeaves;*/
		this.startDate = startDate;
		this.endDate = endDate;
		this.leaveStatus = leaveStatus;
		this.leaveType = leaveType;
		this.leavesId = leavesId;
		this.description = description;
		/*this.days = days;*/
	}



	@Override
	public boolean equals(Object o) {
		if (this == o) {
        	return true;
        } else {
        	return false;
        }		
	}

    @Override
	public int hashCode() {
        int result = 0;
        
        return result;
    }
   
    /**
     * @see java.lang.Object#toString()
     * 
     * Domestic Address Formatted as
     *  addressLine1; addressLine2; city, state   zipCode[-zipCodeSupplement]
     * 
     * Military Address Formatted as
     *  addressLine1; addressLine2; city postalCode;
     * @Override
     */
    
    @Override
	public String toString() {

        StringBuffer buffer = new StringBuffer(DEFAULT_BUFFER_SIZE);
        buffer
            .append(" User Name: ")
            .append(getUsername())
            .append(" Status : ")
            .append(getStatus());
        return buffer.toString();
    }
    @Override
	public int compareTo(Object object) {
    	ViewStaffLeaveDetails myClass = (ViewStaffLeaveDetails) object;
        return new CompareToBuilder().append(this.username,
                        myClass.username).toComparison();
    }
	
    public String getQualification1() {
		return qualification1;
	}

	public void setQualification1(String qualification1) {
		this.qualification1 = qualification1;
	}

	public String getQualification2() {
		return qualification2;
	}

	public void setQualification2(String qualification2) {
		this.qualification2 = qualification2;
	}

	public long getAccountId() {
		return accountId;
	}
	public long getCustId() {
		return custId;
	}
	public String getUsername() {
		return username;
	}
	public String getAcademicYearId() {
		return academicYearId;
	}
	public void setAcademicYearId(String academicYearId) {
		this.academicYearId = academicYearId;
	}
	public String getStatus() {
		return status;
	}
	public long getStaffId() {
		return staffId;
	}
	/*public long getTotalLeaves() {
		return totalLeaves;
	}*/
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	 
	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}
	public void setCustId(long custId) {
		this.custId = custId;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setStaffId(long staffId) {
		this.staffId = staffId;
	}
	/*public void setTotalLeaves(long totalLeaves) {
		this.totalLeaves = totalLeaves;
	}*/
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	 
	/*public long getCasualLeaves() {
		return casualLeaves;
	}
	public long getSickLeaves() {
		return sickLeaves;
	}
	public void setCasualLeaves(long casualLeaves) {
		this.casualLeaves = casualLeaves;
	}
	public void setSickLeaves(long sickLeaves) {
		this.sickLeaves = sickLeaves;
	}*/
	
	public Date getStartDate() {
		return startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public String getLeaveStatus() {
		return leaveStatus;
	}

	public String getLeaveType() {
		return leaveType;
	}

	@Id
	public long getLeavesId() {
		return leavesId;
	}

	public String getDescription() {
		return description;
	}

	/*public double getDays() {
		return days;
	}*/

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public void setLeaveStatus(String leaveStatus) {
		this.leaveStatus = leaveStatus;
	}

	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}

	public void setLeavesId(long leavesId) {
		this.leavesId = leavesId;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/*public void setDays(double days) {
		this.days = days;
	}*/
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

	public long getRoleId() {
		return roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Transient
    public String getFullFormattedName(
        boolean title,
        boolean middle,
        boolean suffix)
    {
        StringBuffer ret = new StringBuffer(10);
         /* if (title && !StringFunctions.isNullOrEmpty(strTitle))
            {
                ret.append(strTitle);
                if (!strTitle.endsWith("."))
                {
                    ret.append(".");
                }
                ret.append(" ");
            }*/
            if (!StringFunctions.isNullOrEmpty(getLastName()))
            {
                ret.append(getLastName());
                
                
            }
           /* if (suffix && !StringFunctions.isNullOrEmpty(strSuffix) 
                    && !Constants.SELECT_ID.equalsIgnoreCase(strSuffix))
            {
                ret.append(" ");
                ret.append(strSuffix);
                ret.append(", ");
            }
            else
            {
                ret.append(", ");
            }*/
            if (!StringFunctions.isNullOrEmpty(getFirstName()))
            {
                ret.append(", ");
                ret.append(getFirstName());
              //  ret.append(" ");
            }
           /* if (middle && !StringFunctions.isNullOrEmpty(strMiddleInitial ))
            {
                ret.append(strMiddleInitial.charAt(0));
                ret.append(" ");
            }*/
        ret.append(" ");
        return ret.toString().trim();
    }
	@Transient
    public String getPersonFirstLastNameOnly()
    {
        StringBuffer ret = new StringBuffer(10);
            if (!StringFunctions.isNullOrEmpty(getFirstName()))
            {
                ret.append(getFirstName());
            }
            if (!StringFunctions.isNullOrEmpty(getLastName()))
            {
                ret.append(" ");
                ret.append(getLastName());
            }
       
        return ret.toString().trim();
    }

    /**
     * Returns the student for display on UI
     * @return string with full formatted student
     */
   @Transient
    public String getDisplayName()
    {
        return getFullFormattedName(false, true, true);
    }
   @Transient
   public String getPersonFullName()
   {
       StringBuffer ret = new StringBuffer(10);
	       
           if (!StringFunctions.isNullOrEmpty(getFirstName()))
           {
               ret.append(getFirstName());
           }
           /*if (!StringFunctions.isNullOrEmpty(getFirstName()))
	       {
        	   ret.append(" ");
	           ret.append(getMiddleName());
	       }*/
           if (!StringFunctions.isNullOrEmpty(getLastName()))
           {
               ret.append(" ");
               ret.append(getLastName());
           }
      
       return ret.toString().trim();
   }
  /* @Transient
   public long getDisplayTotalLeaves()
   {
	  return getCasualLeaves() + getSickLeaves();
       
   }
   @Transient
   public long getDisplayUsedLeaves()
   {
	  return getCasualLeaves() + getSickLeaves();
       
   }*/
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

	/**
	 * @return the roleDescription
	 */
	public String getRoleDescription() {
		return roleDescription;
	}
	
	/**
	 * @param roleDescription the roleDescription to set
	 */
	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}
	
	@Transient
	public int getNoofDays(){
		return DateFunctions.daysBetween(this.getStartDate(), this.getEndDate())+1;
	}
	@Column(name = "permanentOrContract", nullable = true, length = 1)
	public String getPermanentOrContract() {
		return permanentOrContract;
	}

	public void setPermanentOrContract(String permanentOrContract) {
		this.permanentOrContract = permanentOrContract;
	}
	public long getUserEarnedLeaves() {
		return userEarnedLeaves;
	}
	public void setUserEarnedLeaves(long userEarnedLeaves) {
		this.userEarnedLeaves = userEarnedLeaves;
	} 
	
}