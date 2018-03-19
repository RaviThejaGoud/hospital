package com.urt.persistence.model.study;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.churchgroup.util.date.DateFormatter;
import com.churchgroup.util.date.DateFunctions;
import com.churchgroup.util.string.StringFunctions;
import com.urt.persistence.model.common.UserImage;


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
 *  1.0    Nov 04, 2010     Narahari          	Created
/********************************************************************/

@Entity
@Table(name = "vw_studentLeaveDetails")
public class ViewStudentLeaveDetails  implements Serializable,Cloneable,Comparable {

	private static final Log log = LogFactory.getLog(ViewStudentLeaveDetails.class);
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
     private String sharePrivacy;
     private String userOnlineNow;
     private long academicYearId;
     private String status;
     private long studentId;
     private String firstName;
     private String lastName;
     private String middleName;
     private String fatherName;
     private String motherName;
     //private String lastUpdatedBy;
 	// protected Date lastUpdatedDate;
     //protected Date createdDate;
     protected String addressLine1;
     protected String addressLine2;
     protected String city;
     protected String state;
     protected String postalCode;
     protected String mobileNumber;
     protected String phoneNumber;
     protected String mothersMaidenName;
     protected String bloodGroup;
     protected String dateOfBirth;
     protected long classId;
     protected String rollNumber;
     //protected long sectionId;
     protected String className;
     protected String section;
     protected String parentId;
     private long roleId;
     private String roleName;
     private String leaveStatus;
     private String leaveType;
     private long leavesId;
     private Date startDate;
     private Date endDate;
     private String description;
     /*private double days;*/
     private String startTime;
     private String endTime;
     //private String boardingPoint;
     private long leaveSupervisorId;
     private String parentEmail;
     private String imageName;
     private String thumbNail;
     private String imagePath;
     private String imageId;
     private String dateOfJoining;
     private String summary;
     private double totalLeavesTaken;
     private String roleDescription;
     private long  classSectionId;
     private double leavesCount;
     private String hostelMode;
     private boolean  halfDay;
     private String sessionType;
     
    public boolean isHalfDay() {
 		return halfDay;
 	}

 	public void setHalfDay(boolean halfDay) {
 		this.halfDay = halfDay;
 	}

 	public String getSessionType() {
 		return sessionType;
 	}

 	public void setSessionType(String sessionType) {
 		this.sessionType = sessionType;
 	}
     
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getHostelMode() {
		return hostelMode;
	}

	public void setHostelMode(String hostelMode) {
		this.hostelMode = hostelMode;
	}

	public double getLeavesCount() {
		return leavesCount;
	}
	
	public void setLeavesCount(double leavesCount) {
		this.leavesCount = leavesCount;
	}
	/**
	 * @return the classSectionId
	 */
	public long getClassSectionId() {
		return classSectionId;
	}
	/**
	 * @param classSectionId the classSectionId to set
	 */
	public void setClassSectionId(long classSectionId) {
		this.classSectionId = classSectionId;
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
     
    	
    public String getImageId() {
 		return imageId;
 	}
 	public void setImageId(String imageId) {
 		this.imageId = imageId;
 	}
     
 	
 	public String getImagePath() {
 		return imagePath;
 	}
 	public void setImagePath(String imagePath) {
 		this.imagePath = imagePath;
 	}
   public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	public String getThumbNail() {
		return thumbNail;
	}
	public void setThumbNail(String thumbNail) {
		this.thumbNail = thumbNail;
	}
     
   	
   	
    public String getParentEmail() {
 		return parentEmail;
 	}
 	public void setParentEmail(String parentEmail) {
 		this.parentEmail = parentEmail;
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

	public Date getStartDate() {
		return startDate;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/*public double getDays() {
		return days;
	}

	public void setDays(double days) {
		this.days = days;
	}*/

	public String getLeaveStatus() {
		return leaveStatus;
	}

	public void setLeaveStatus(String leaveStatus) {
		this.leaveStatus = leaveStatus;
	}

	public String getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}

	@Id
	public long getLeavesId() {
		return leavesId;
	}

	public void setLeavesId(long leavesId) {
		this.leavesId = leavesId;
	}
    
    public ViewStudentLeaveDetails(long accountId, long custId,
			String username, String sharePrivacy, String userOnlineNow,
			long academicYearId,String status,long studentId,
			String firstName, String lastName,
			String middleName) {
		super();
		this.accountId = accountId;
		this.custId = custId;
		this.username = username;
		this.sharePrivacy = sharePrivacy;
		this.userOnlineNow = userOnlineNow;
		this.academicYearId = academicYearId;
		this.status = status;
		this.studentId = studentId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.middleName = middleName;
		this.fatherName = fatherName;
		this.motherName = motherName;
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
    	ViewStudentLeaveDetails myClass = (ViewStudentLeaveDetails) object;
        return new CompareToBuilder().append(this.username,
                        myClass.username).toComparison();
    }
    
	public long getClassId() {
		return classId;
	}
	public void setClassId(long classId) {
		this.classId = classId;
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
	public String getSharePrivacy() {
		return sharePrivacy;
	}
	public String getUserOnlineNow() {
		return userOnlineNow;
	}
	public String getStatus() {
		return status;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getMiddleName() {
		return middleName;
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
	public void setSharePrivacy(String sharePrivacy) {
		this.sharePrivacy = sharePrivacy;
	}
	public void setUserOnlineNow(String userOnlineNow) {
		this.userOnlineNow = userOnlineNow;
	}
	public long getAcademicYearId() {
		return academicYearId;
	}
	public void setAcademicYearId(long academicYearId) {
		this.academicYearId = academicYearId;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
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
	public long getStudentId() {
		return studentId;
	}
	public void setStudentId(long studentId) {
		this.studentId = studentId;
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
   /*@Transient
   public long getDisplayTotalLeaves()
   {
	  return getCasualLeaves() + getSickLeaves();
       
   }
   @Transient
   public long getDisplayUsedLeaves()
   {
	  return getCasualLeaves() + getSickLeaves();
       
   }*/
/**
 * @return the addressLine1
 */
public String getAddressLine1() {
	return addressLine1;
}
/**
 * @param addressLine1 the addressLine1 to set
 */
public void setAddressLine1(String addressLine1) {
	this.addressLine1 = addressLine1;
}
/**
 * @return the addressLine2
 */
public String getAddressLine2() {
	return addressLine2;
}
/**
 * @param addressLine2 the addressLine2 to set
 */
public void setAddressLine2(String addressLine2) {
	this.addressLine2 = addressLine2;
}
/**
 * @return the city
 */
public String getCity() {
	return city;
}
/**
 * @param city the city to set
 */
public void setCity(String city) {
	this.city = city;
}
/**
 * @return the state
 */
public String getState() {
	return state;
}
/**
 * @param state the state to set
 */
public void setState(String state) {
	this.state = state;
}
/**
 * @return the postalCode
 */
public String getPostalCode() {
	return postalCode;
}
/**
 * @param postalCode the postalCode to set
 */
public void setPostalCode(String postalCode) {
	this.postalCode = postalCode;
}
/**
 * @return the mobileNumber
 */
public String getMobileNumber() {
	return mobileNumber;
}
/**
 * @param mobileNumber the mobileNumber to set
 */
public void setMobileNumber(String mobileNumber) {
	this.mobileNumber = mobileNumber;
}
/**
 * @return the phoneNumber
 */
public String getPhoneNumber() {
	return phoneNumber;
}
/**
 * @param phoneNumber the phoneNumber to set
 */
public void setPhoneNumber(String phoneNumber) {
	this.phoneNumber = phoneNumber;
}
/**
 * @return the mothersMaidenName
 */
public String getMothersMaidenName() {
	return mothersMaidenName;
}
/**
 * @param mothersMaidenName the mothersMaidenName to set
 */
public void setMothersMaidenName(String mothersMaidenName) {
	this.mothersMaidenName = mothersMaidenName;
}
/**
 * @return the bloodGroup
 */
public String getBloodGroup() {
	return bloodGroup;
}
/**
 * @param bloodGroup the bloodGroup to set
 */
public void setBloodGroup(String bloodGroup) {
	this.bloodGroup = bloodGroup;
}
/**
 * @return the dateOfBirth
 */
public String getDateOfBirth() {
	return dateOfBirth;
}
/**
 * @param dateOfBirth the dateOfBirth to set
 */
public void setDateOfBirth(String dateOfBirth) {
	this.dateOfBirth = dateOfBirth;
}

public long getRoleId() {
	return roleId;
}

public void setRoleId(long roleId) {
	this.roleId = roleId;
}

public String getRoleName() {
	return roleName;
}

public void setRoleName(String roleName) {
	this.roleName = roleName;
}

public String getClassName() {
	return className;
}
public String getSection() {
	return section;
}
public void setClassName(String className) {
	this.className = className;
}
public void setSection(String section) {
	this.section = section;
}

public String getRollNumber() {
	return rollNumber;
}
/*public long getSectionId() {
	return sectionId;
}*/
public void setRollNumber(String rollNumber) {
	this.rollNumber = rollNumber;
}
/*public void setSectionId(long sectionId) {
	this.sectionId = sectionId;
}*/
public ViewStudentLeaveDetails()
{
    super();
}

/**
 * @return the fatherName
 */
 @Transient
public String getIdAndName(){
	return getPersonFirstLastNameOnly()+" ("+getUsername()+")";
}
public String getFatherName() {
	return fatherName;
}
/**
 * @param fatherName the fatherName to set
 */
public void setFatherName(String fatherName) {
	this.fatherName = fatherName;
}
/**
 * @return the motherName
 */
public String getMotherName() {
	return motherName;
}
/**
 * @param motherName the motherName to set
 */
public void setMotherName(String motherName) {
	this.motherName = motherName;
}
@Transient
public String getAddressDesc() {

        StringBuffer buffer = new StringBuffer(DEFAULT_BUFFER_SIZE);
        
        if(!StringFunctions.isNullOrEmpty(this.addressLine1))
        {
            buffer.append(getAddressLine1());
        }
        if(!StringFunctions.isNullOrEmpty(this.addressLine2))
        {
            buffer.append(", ").append(getAddressLine2());
        }
        if(!StringFunctions.isNullOrEmpty(this.city))
        {
            buffer.append(", ").append(getCity());
        }
        if(!StringFunctions.isNullOrEmpty(this.state))
        {
            buffer.append(", ").append(getState());
        }
        if(!StringFunctions.isNullOrEmpty(this.postalCode))
        {
            buffer.append(", ").append(getPostalCode());
        }
           
        return buffer.toString();
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
	public String getStartDateFormat() {
		return DateFormatter.formatDate(DateFormatter.MMDDYYYY_PATTERN, this.startDate);
	}
	@Transient
	public String getEndDateFormat() {
		return DateFormatter.formatDate(DateFormatter.MMDDYYYY_PATTERN, this.endDate);
	}
	
	@Transient
	   public String getLeaveStatusDesc() {
			String type;
			if("CL".equalsIgnoreCase(getLeaveType()))
	   	{
				type = "Causual";
	   	}
			else
			{
				type = "Sick";
			}
					
	       return type;

	   }
	@Transient
    public String getClassAndSection()
    {
    	if(StringFunctions.isNullOrEmpty(getSection()))
    	{
    		return getClassName();
    	}
    	return getClassName()+" - "+getSection();
    }
	
//UserImage methods
	
	@Transient
    public static String getImageNotFoundFile()
    {
        return "/images/common/photo_notAvailable.jpg"; 
    }
    @Transient
    public static String getStudyImageNotFoundFile()
    {
        return "/images/common/studyImageNotAvailable.jpg"; 
    }
    @Transient
    public String getOriginalAttachmentFilePath()
    {
        if(!StringFunctions.isNullOrEmpty(getImagePath()))
        {
            return getImagePath().concat(getImageName());
        }
        return UserImage.getStudyImageNotFoundFile();
    }
    @Transient
    public String getAdjustedAttachmentFilePath()
    {
        if(!StringFunctions.isNullOrEmpty(getImagePath()))
        {
        	log.debug(getImagePath().concat(getThumbNail())); 
            return getImagePath().concat(getThumbNail());
        }
        return UserImage.getStudyImageNotFoundFile();
    }
    @Transient
    public String getHrefOriginalAttachmentFilePath()
    {
        if(!StringFunctions.isNullOrEmpty(getImagePath()))
        {
            return getImagePath().substring(1).concat(getImageName());
        }
        return UserImage.getStudyImageNotFoundFile();
    }
    @Transient
    public String getHrefAdjustedAttachmentFilePath()
    {
        if(!StringFunctions.isNullOrEmpty(getImagePath()))
        {
            return getImagePath().substring(1).concat(getThumbNail());
        }
        return UserImage.getStudyImageNotFoundFile();
    }
    @Transient
	public int getNoofDays(){
		return DateFunctions.daysBetween(this.getStartDate(), this.getEndDate())+1;
	}
    @Transient
	public String getAccountIdAndClassSectionId() {
		return  getAccountId()+"_"+getClassSectionId();
	}
    @Transient
	public String getAccountIdClassSectionIdAcademicYearIdAndCustId() {
		return  getAccountId()+"_"+getClassSectionId()+"_"+getAcademicYearId()+"_"+getCustId();
	}
}
      
