/**
 * 
 */
package com.urt.persistence.model.common;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.urt.persistence.model.base.PersistentObject;
import com.urt.persistence.model.user.User;

/**
 * @author sunanda
 *
 */
@Entity
@Table(name = "circular")
public class Circular extends PersistentObject {
	
	private static final long serialVersionUID = 1L;
	private long senderAccountId;
    private String circularDate;
    private String type;
    private String circularDescription;
    private long custId;
    private long academicYear;
    protected Set<User> users;
    private String circularStatus;
     
     @ManyToMany(cascade = CascadeType.ALL)
     @JoinTable(name = "CircularUsers",
        joinColumns = { @JoinColumn(name = "circularId") },
        inverseJoinColumns = { @JoinColumn(name = "userId") })
     public Set<User> getUsers() {
         if(users == null)
         {
             users = new HashSet<User>();
         }
         return users;
     }

	/**
	 * @return the circularDate
	 */
	public String getCircularDate() {
		return circularDate;
	}

	/**
	 * @param circularDate the circularDate to set
	 */
	public void setCircularDate(String circularDate) {
		this.circularDate = circularDate;
	}

	public void addNewUser(List<User> users) {
    	 getUsers().addAll(users);
     }
     
    public void setUsers(Set<User> users) {
         this.users = users;
     }
	/**
	 * @return the academicYear
	 */
    @Column(name = "academicYearId", columnDefinition="int default 0")
	public long getAcademicYear() {
		return academicYear;
	}

	/**
	 * @param academicYear the academicYear to set
	 */
	public void setAcademicYear(long academicYear) {
		this.academicYear = academicYear;
	}
	/**
	 * @return the senderAccountId
	 */
	public long getSenderAccountId() {
		return senderAccountId;
	}

	/**
	 * @param senderAccountId the senderAccountId to set
	 */
	public void setSenderAccountId(long senderAccountId) {
		this.senderAccountId = senderAccountId;
	}
	
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	 
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the circularDescription
	 */
	public String getCircularDescription() {
		return circularDescription;
	}

	/**
	 * @param circularDescription the circularDescription to set
	 */
	public void setCircularDescription(String circularDescription) {
		this.circularDescription = circularDescription;
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

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "";
	}
	@Column(name = "circularStatus", nullable = true, length = 3)
	public String getCircularStatus() {
		return circularStatus;
	}

	public void setCircularStatus(String circularStatus) {
		this.circularStatus = circularStatus;
	}
}
