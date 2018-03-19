package com.urt.persistence.model.exam;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.churchgroup.util.date.DateFormatter;
import com.urt.persistence.model.base.PersistentObject;

/*
 * @create new table Syllabus.
 */
/**
 * @author urt
 *
 */
@Entity
@Table(name = "commonType")
public class CommonType  extends PersistentObject {
	
    private static final long serialVersionUID = 3832626162173359411L;
   
	protected long custId;
	private String skillTypeName;
	private String type;
	
	
	@Column(name = "skillTypeName", nullable = true, length = 65)
	public String getSkillTypeName() {
		return skillTypeName;
	}
	public void setSkillTypeName(String skillTypeName) {
		this.skillTypeName = skillTypeName;
	}
	@Column(name = "type", nullable = true, length = 65)
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
	public CommonType() {
        
    }
    public CommonType(long id) {
        setId(id);
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
	
	
	@Column(name="custId", nullable=true, length = 10)
	public long getCustId() {
		return custId;
	}
	public void setCustId(long custId) {
		this.custId = custId;
	}
	
	@Transient
	public String getCreatedDateStr() {
		try{
			return DateFormatter.formatDate(DateFormatter.MMDDCCYY_PATTERN, this.getCreatedDate());
		}
		catch(Exception ex){
			return "";
		}
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "";
	}
	@Override
	public int compareTo(Object object) {
		// TODO Auto-generated method stub
		return 0;
	}
}

