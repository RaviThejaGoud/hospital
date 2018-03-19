package com.urt.persistence.model.hostel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.urt.persistence.model.base.PersistentObject;
import com.urt.persistence.model.common.AcademicYear;

/*
 * @create new table customer.
 */
@Entity
@Table(name = "hostelStudents")
public class HostelStudents  extends PersistentObject {
	
	 private static final long serialVersionUID = 1L;

	public HostelStudents() {
        
    }

    public HostelStudents(long id) {
        setId(id);
    }

    private long accountId;
    private long custId;
    private long roomId;
    private AcademicYear academicYear;
    protected String status;
    protected long studentId;
    private long bedId;

    

    
    public long getAccountId() {
		return accountId;
	}

	@Column(name = "status", nullable = true, length = 1,columnDefinition="char(1) default 'Y'")
    public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getStudentId() {
		return studentId;
	}

	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}

	public long getBedId() {
		return bedId;
	}

	public void setBedId(long bedId) {
		this.bedId = bedId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	public void setCustId(long custId) {
		this.custId = custId;
	}

	public void setRoomId(long roomId) {
		this.roomId = roomId;
	}

	public Long getCustId() {
        return custId;
    }

    /**
     * @param custId the custId to set
     */
    public void setCustId(Long custId) {
        this.custId = custId;
    }

    @OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="academicYearId", insertable=true, updatable=true) 
	public AcademicYear getAcademicYear() {
		return academicYear;
	}
	public void setAcademicYear(AcademicYear academicYear) {
		this.academicYear = academicYear;
	}
   
    /**
     * @param accountId the accountId to set
     */
    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    /**
     * @return the roomId
     */
    public Long getRoomId() {
        return roomId;
    }

    /**
     * @param roomId the roomId to set
     */
    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    /**
	 * @return the customer name.
	 */
     
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
        .append("id", this.getId())
        .toString();
	}

	@Override
	public int compareTo(Object object) {
		return 0;
	}

	@Override
	public boolean equals(Object o) {
		return false;
	}

	@Override
	public int hashCode() {
		return 0;
	}

}
