package com.urt.persistence.model.common;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.churchgroup.util.date.DateFormatter;
import com.hyniva.sms.ws.vo.StaffStatutoryVO;
import com.urt.persistence.model.base.PersistentObject;


/********************************************************************
 * Copyright (C) 2005-06
 * IFS
 * All Rights Reserved 
 *
  * File: StaffStatutory.java
 ********************************************************************
 *
 *  Ver   Date              Name               Description
 *  ====  ========          ============       ==================
 *  1.0    Jul 16, 2012     Madhavi          	Created
/********************************************************************/


@Entity
@Table(name = "staffStatutory")
public class StaffStatutory extends PersistentObject {
    
    private static final long serialVersionUID = 3690197650654049848L;
    
    
    private String esiNo;
    private Date esiDateofJoin;
    private double esiPercentage;
    private String pfNo;
    private Date pfDateofJoin;
    private double pfPercentage;
    private long custId;
    
    private double esiPercentageValue;
    private double pfPercentageValue;
    
    
    @Transient
	public double getEsiPercentageValue() {
		return this.esiPercentageValue;
	}
	public void setEsiPercentageValue(double esiPercentageValue) {
		this.esiPercentageValue = esiPercentageValue;
	}
	@Transient
	public double getPfPercentageValue() {
		return this.pfPercentageValue;
	}
	public void setPfPercentageValue(double pfPercentageValue) {
		this.pfPercentageValue = pfPercentageValue;
	}
    
	public String getEsiNo() {
		return esiNo;
	}
	public void setEsiNo(String esiNo) {
		this.esiNo = esiNo;
	}
	public Date getEsiDateofJoin() {
		return esiDateofJoin;
	}
	public void setEsiDateofJoin(Date esiDateofJoin) {
		this.esiDateofJoin = esiDateofJoin;
	}
	public double getEsiPercentage() {
		return this.esiPercentage;
	}
	public void setEsiPercentage(double esiPercentage) {
		this.esiPercentage = esiPercentage;
	}
	public String getPfNo() {
		return pfNo;
	}
	public void setPfNo(String pfNo) {
		this.pfNo = pfNo;
	}
	public Date getPfDateofJoin() {
		return pfDateofJoin;
	}
	public void setPfDateofJoin(Date pfDateofJoin) {
		this.pfDateofJoin = pfDateofJoin;
	}
	public double getPfPercentage() {
		return this.pfPercentage;
	}
	public void setPfPercentage(double pfPercentage) {
		this.pfPercentage = pfPercentage;
	}
	public long getCustId() {
		return custId;
	}
	public void setCustId(long custId) {
		this.custId = custId;
	}
    
	@Override
    public boolean equals(Object o) 
	{
		return false;
        

    }
    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE)
                .append(" Id: ")
                .append(getId())
                .append(" Version: ")
                
                .toString();
    }

    @Override
    public int compareTo(Object object) 
    {
		return 0;
    	
    }
    
    @Transient
    public String getEsiDateofJoinStr() {
        return DateFormatter.formatDate(DateFormatter.ddMMMyyyy_PATTERN1,this.esiDateofJoin);

    }
    @Transient
    public String getPfDateofJoinStr() {
        return DateFormatter.formatDate(DateFormatter.ddMMMyyyy_PATTERN1,this.pfDateofJoin);

    }
	@Transient
    public Date getStrDateofJoin(String dateofJoinStr) 
	{
		log.debug(DateFormatter.parseString(DateFormatter.YYYY_MM_DD_HHMMSS_PATTERN,DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_HHMMSS_PATTERN,dateofJoinStr)));
		return  DateFormatter.parseString(DateFormatter.YYYY_MM_DD_HHMMSS_PATTERN,DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_HHMMSS_PATTERN,dateofJoinStr));
	}
	
	@Transient
	public String getStatutoryStatusDesc() 
	{
		if("AW".equalsIgnoreCase(""))
		{
			return "Allowance";
		}
		else
			return "Employer Statutory Contribution";
	}
	
	@Transient
	public StaffStatutory copyFromVoToEntity(StaffStatutory staffStatutory, StaffStatutoryVO staffStatutoryvoVo)
	{
		if(staffStatutory.getId() == 0)
			staffStatutory.id = staffStatutoryvoVo.id;
		staffStatutory.esiNo = staffStatutoryvoVo.esiNo;
		staffStatutory.esiDateofJoin = staffStatutoryvoVo.esiDateofJoin;
		staffStatutory.esiPercentage = staffStatutoryvoVo.esiPercentage;
		staffStatutory.pfNo = staffStatutoryvoVo.pfNo;
		staffStatutory.pfDateofJoin = staffStatutoryvoVo.pfDateofJoin;
		staffStatutory.pfPercentage = staffStatutoryvoVo.pfPercentage;
		staffStatutory.custId = staffStatutoryvoVo.custId;
		staffStatutory.esiPercentageValue = staffStatutoryvoVo.esiPercentageValue;
		staffStatutory.pfPercentageValue = staffStatutoryvoVo.pfPercentageValue;
		return staffStatutory;
	}
	@Transient
	public StaffStatutoryVO copyFromEntityToVo(StaffStatutory staffStatutory)
	{
		StaffStatutoryVO staffStatutoryVo = new StaffStatutoryVO();
		
		staffStatutoryVo.id = staffStatutory.id;
		staffStatutoryVo.esiNo = staffStatutory.esiNo;
		staffStatutoryVo.esiDateofJoin = staffStatutory.esiDateofJoin;
		staffStatutoryVo.esiPercentage = staffStatutory.esiPercentage;
		staffStatutoryVo.pfNo = staffStatutory.pfNo;
		staffStatutoryVo.pfDateofJoin = staffStatutory.pfDateofJoin;
		staffStatutoryVo.pfPercentage = staffStatutory.pfPercentage;
		staffStatutoryVo.custId = staffStatutory.custId;
		staffStatutoryVo.esiPercentageValue = staffStatutory.esiPercentageValue;
		staffStatutoryVo.pfPercentageValue = staffStatutory.pfPercentageValue;
		return staffStatutoryVo;
	}
}
