package com.urt.persistence.model.exam;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.urt.persistence.model.base.PersistentObject;

/*
 * @create new table Syllabus.
 */
/**
 * @author urt
 *
 */
@Entity
@Table(name = "kBankType")
public class KBankType  extends PersistentObject {
	
    private static final long serialVersionUID = 3832626162173359411L;
   
    private String typeName;
	private String status;
	protected long custId;
	private int kBankCount;
	
	public KBankType() {
        
    }
    public KBankType(long id) {
        setId(id);
    }
    
    @Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
        .append("id", this.getId())
        .append("typeName", this.typeName).toString();
  
	}
    @Override
	public int compareTo(Object object) {
    	KBankType target = (KBankType) object;		 
        if (target.getTypeName() != null && this.getTypeName() != null)
        {
            if(this.getTypeName().equalsIgnoreCase(target.getTypeName()))
                return 0;
            else 
               return target.getTypeName().compareToIgnoreCase(this.getTypeName());
        }
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
	
	@Column(name = "status", nullable = true, length = 1,columnDefinition="char(1) default 'Y'")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Column(name="typeName", nullable=true, length = 256)
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	@Column(name="custId", nullable=true, length = 10)
	public long getCustId() {
		return custId;
	}
	public void setCustId(long custId) {
		this.custId = custId;
	}
	@Transient
	public int getKBankCount() {
		return kBankCount;
	}
	public void setKBankCount(int kBankCount) {
		this.kBankCount = kBankCount;
	}
	
	
}

