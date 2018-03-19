/**
 * 
 */
package com.urt.persistence.model.study;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang.builder.CompareToBuilder;

import com.urt.persistence.model.base.PersistentObject;

/**
 * @author urtuser
 *
 */
@Entity
@Table(name = "schoolCategory")
public class SchoolCategory extends PersistentObject{

	/**
     * 
     */
	private static final long serialVersionUID = 1L;
	
	private long custId;
	private String categoryName;
    
    
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
	
	
	/**
	 * @return the categoryName
	 */
	public String getCategoryName() {
		return categoryName;
	}
	/**
	 * @param categoryName the categoryName to set
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	@Override
	public int compareTo(Object object) {
		// TODO Auto-generated method stub
		SchoolCategory catecory = (SchoolCategory) object;
        return new CompareToBuilder().append(this.getId(), catecory.getId()).toComparison();
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
