package com.urt.persistence.model.hostel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.annotations.Type;

import com.hyniva.sms.ws.vo.BedVo;
import com.urt.persistence.model.base.PersistentObject;
import com.urt.persistence.model.common.AcademicYear;

/*
 * @create new table customer.
 */
@Entity
@Table(name = "bed")
public class Bed  extends PersistentObject {
	
	 private static final long serialVersionUID = 1L;
	 

	public Bed() {
        
    }

    public Bed(long id) {
        setId(id);
    }

    //private String bedNumber;
    private Long accountId;
    private Long custId;
    private Long roomId;
    private AcademicYear academicYear;
    protected long bedLevel;
   // protected String roomName;
    protected String bedName;
    protected double bedCost;
   // protected String floorName;
    protected boolean status;

    

    /**
	 * @return the status
	 */
    @Column(name = "status", nullable = true, length = 1,columnDefinition="char(1) default 'N'")
    @Type(type="yes_no")
	public boolean isStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(boolean status) {
		this.status = status;
	} 
	
    /**
	 * @return the bedCost
	 */
    @Column(name = "bedCost",nullable = false,columnDefinition = "double default 0" )
	public double getBedCost() {
		return bedCost;
	}

	/**
	 * @param bedCost the bedCost to set
	 */
	public void setBedCost(double bedCost) {
		this.bedCost = bedCost;
	}

	/**
     * @return the bedName
     */
    public String getBedName() {
        return bedName;
    }

    /**
     * @param bedName the bedName to set
     */
    public void setBedName(String bedName) {
        this.bedName = bedName;
    }

   
    /**
     * @return the bedLevel
     */
    public long getBedLevel() {
        return bedLevel;
    }

    /**
     * @param bedLevel the bedLevel to set
     */
    public void setBedLevel(long bedLevel) {
        this.bedLevel = bedLevel;
    }

    /**
     * @return the custId
     */
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
     * @return the accountId
     */
    public Long getAccountId() {
        return accountId;
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

	
	
	public Bed copyFromVoToEntity(Bed bed, BedVo bedVo)
	{
		if(bed.getId() == 0)
			this.id = bedVo.id;
		this.accountId = bedVo.accountId;
		this.custId = bedVo.custId;
		this.roomId = bedVo.roomId;
		this.bedLevel = bedVo.bedLevel;
		this.bedName = bedVo.bedName;
		this.bedCost = bedVo.bedCost;
		this.status = bedVo.status;
		
		return bed;
		
	}
	
	public BedVo copyFromEntityToVo(Bed bed)
	{
		BedVo bedVo = new BedVo();
		
		bedVo.accountId = bed.accountId;
		bedVo.custId = bed.custId;
		bedVo.roomId = bed.roomId;
		bedVo.bedLevel = bed.bedLevel;
		bedVo.bedName = bed.bedName;
		bedVo.bedCost = bed.bedCost;
		bedVo.status = bed.status;
		
		return bedVo;
	}
	
}
