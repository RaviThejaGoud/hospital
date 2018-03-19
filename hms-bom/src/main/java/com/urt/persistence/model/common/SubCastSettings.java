package com.urt.persistence.model.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.urt.persistence.model.base.PersistentObject;

/*
 * @create new table customer.
 */
@Entity
@Table(name = "subCastSettings")
public class SubCastSettings  extends PersistentObject {
	
	private static final long serialVersionUID = 1L;

	/**
	 * @return the studentId
	 */
	public SubCastSettings() {
    }
    public SubCastSettings(long id) {
        setId(id);
    }
    
    //private String state;
    //private String castName;
    private String subCastName;
    private long custId;
    private CastSettings castSettings;
    
 
    
    /**
	 * @return the castSettings
	 */
    @ManyToOne
	@JoinColumn(name="castId", insertable=false, updatable=false)
	@javax.xml.bind.annotation.XmlTransient()
	public CastSettings getCastSettings() {
		return castSettings;
	}
	/**
	 * @param castSettings the castSettings to set
	 */
	public void setCastSettings(CastSettings castSettings) {
		this.castSettings = castSettings;
	}
	@Column(name = "custId", nullable = true, length = 20)
    public long getCustId() {
		return custId;
	}
	public void setCustId(long custId) {
		this.custId = custId;
	}
     
	/*@Column(name = "state", nullable = true, length = 150)
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	@Column(name = "castName", nullable = true, length = 200)
	public String getCastName() {
		return castName;
	}
	public void setCastName(String castName) {
		this.castName = castName;
	}*/
	
	@Column(name = "subCastName", nullable = true, length = 200)
	 	public String getSubCastName() {
		return subCastName;
	}
	public void setSubCastName(String subCastName) {
		this.subCastName = subCastName;
	}
	
	
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
}
