package com.urt.persistence.model.study;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.churchgroup.util.object.ObjectFunctions;
import com.urt.persistence.model.base.PersistentObject;


@Entity
@Table(name = "schoolShiftInfo")
public class SchoolShiftInfo  extends PersistentObject {

    private static final long serialVersionUID = -9190968485277417762L;

    private String startTime;
    private String endTime;
    private String status;
    private String shiftName;
    private long custId;
    private long academicYearId;
    private Set<Staff> staffShiftInfo;
    private String isStaffOrNonStaff;
    
	
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

	@Column(name = "status", nullable = true, length = 1,columnDefinition="char(1) default 'Y'")
    public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	/** default constructor */
    public SchoolShiftInfo() {
    }
    
    /** full constructor */

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-465592447, 1546771509).append(
                this.id).append(this.id).toHashCode();
    }

    /**
     * @see java.lang.Object#equals(Object)
     */
    @Override
	public boolean equals(Object object) {
        if (!(object instanceof SchoolShiftInfo)) {
            return false;
        }
        SchoolShiftInfo rhs = (SchoolShiftInfo) object;
        return new EqualsBuilder().append(this.status, rhs.status)
                .append(this.status, rhs.status).isEquals();
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", this.getId())
                
                .toString();
    }

    /**
     * @see java.lang.Comparable#compareTo(Object)
     */
    public int compareTo(Object object) {
		return 0;
    }
    @Column(name = "shiftName", nullable = false, unique=false, length = 128)
	public String getShiftName() {
		return shiftName;
	}

	public void setShiftName(String shiftName) {
		this.shiftName = shiftName;
	}

	public long getCustId() {
		return custId;
	}

	public void setCustId(long custId) {
		this.custId = custId;
	}

	public long getAcademicYearId() {
		return academicYearId;
	}

	public void setAcademicYearId(long academicYearId) {
		this.academicYearId = academicYearId;
	}
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="shiftId",updatable=true, nullable=true) 
	public Set<Staff> getStaffShiftInfo() {
		return staffShiftInfo;
	}

	public void setStaffShiftInfo(Set<Staff> staffShiftInfo) {
		this.staffShiftInfo = staffShiftInfo;
	}
	public void addSchoolShiftInfo(Staff staffShiftInfo){
		if(ObjectFunctions.isNullOrEmpty(this.getStaffShiftInfo())){
			this.staffShiftInfo=new HashSet<Staff>();
		}
		this.staffShiftInfo.add(staffShiftInfo);
	}
	@Column(name = "isStaffOrNonStaff", nullable = true, length = 1,columnDefinition="char(1) default 'S'")
	public String getIsStaffOrNonStaff() {
		return isStaffOrNonStaff;
	}

	public void setIsStaffOrNonStaff(String isStaffOrNonStaff) {
		this.isStaffOrNonStaff = isStaffOrNonStaff;
	}
	
}