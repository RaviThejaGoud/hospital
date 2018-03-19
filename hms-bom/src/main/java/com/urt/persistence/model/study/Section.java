package com.urt.persistence.model.study;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.churchgroup.util.date.DateFunctions;
import com.urt.persistence.model.base.PersistentObject;

@Entity
@Table(name = "section")
public class Section  extends PersistentObject {
	
    private static final long serialVersionUID = 3832626162173359411L;
    
    protected String section; 
    protected long custId; 
    
	/**
	 * @return the section
	 */
	public String getSection() {
		return section;
	}

	/**
	 * @param section the section to set
	 */
	public void setSection(String section) {
		this.section = section;
	}

	public Section() {
        this.createdDate=DateFunctions.getTodayPlusNdays(0);
        this.lastUpdatedDate=DateFunctions.getTodayPlusNdays(0);
        this.lastAccessDate=DateFunctions.getTodayPlusNdays(0);
    }
    
	/**
	 * @return the custId
	 */
	public long getCustId() {
		return custId;
	}

	/**
	 * @param custId the custId to set
	 */
	public void setCustId(long custId) {
		this.custId = custId;
	}
	@Transient
	public String getSectionName()
	{
		 StringBuffer ret = new StringBuffer(10);
		 ret.append("Section ");
		 ret.append(getSection());
		 
		return ret.toString();
	}

    @Override
	public int compareTo(Object object) {
    	Section target = (Section) object;
    	if (target.getSection() != null && this.getSection() != null)
        {
          return this.getSection().compareToIgnoreCase(target.getSection());
        }
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

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "";
	}
		
}
    

  

