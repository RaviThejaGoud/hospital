package com.urt.persistence.model.common;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.churchgroup.util.object.ObjectFunctions;
import com.urt.persistence.model.base.PersistentObject;

/*
 * @create new table customer.
 */
@Entity
@Table(name = "castSettings")
public class CastSettings  extends PersistentObject {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Default size for StringBuffer initialization
	 */
	/**
	 * @return the studentId
	 */
	public CastSettings() {
    }
    public CastSettings(long id) {
        setId(id);
    }
    
    private String castName;
    private String subCastName;
    private long custId;
    @javax.xml.bind.annotation.XmlTransient()
    protected Set<SubCastSettings> subCastSettings;
    public void addSubCast(SubCastSettings subCastSettings) {
        if(ObjectFunctions.isNullOrEmpty(getSubCastSettings())){
          this.subCastSettings=new HashSet<SubCastSettings>();
        }
        getSubCastSettings().add(subCastSettings);
}
    
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    @Fetch(FetchMode.JOIN) 
 	@JoinColumn(name="castId")
    public Set<SubCastSettings> getSubCastSettings() {
		return this.subCastSettings;
	}
	public void setSubCastSettings(Set<SubCastSettings> subCastSettings) {
		this.subCastSettings = subCastSettings;
	}
	@Column(name = "custId", nullable = true, length = 20)
    public long getCustId() {
		return custId;
	}
	public void setCustId(long custId) {
		this.custId = custId;
	}
     
	@Column(name = "castName", nullable = true, length = 200)
	public String getCastName() {
		return castName;
	}
	public void setCastName(String castName) {
		this.castName = castName;
	}
	
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
	
	public void addSubCastSettings(SubCastSettings subCastSet) {
		if(ObjectFunctions.isNullOrEmpty(this.getSubCastSettings())){
			this.subCastSettings=new HashSet<SubCastSettings>();
		}
		this.subCastSettings.add(subCastSet);
	}
}
