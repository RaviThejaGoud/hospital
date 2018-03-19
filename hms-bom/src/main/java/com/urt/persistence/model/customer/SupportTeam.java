package com.urt.persistence.model.customer;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.urt.persistence.model.base.PersistentObject;
/*
 * @create new table customer.
 */
@Entity
@Table(name = "supportTeam")
public class SupportTeam  extends PersistentObject {
	
    private static final long serialVersionUID = 3832626162173359411L;
    
   
    protected String email;
    protected String firstName;
    protected String lastName;
    protected String mobileNumber;
    protected String type; // D - Developer, S - Support, T - Testing                  
	protected String status; // Y - Active, N - InActive
	 

	/**
	 * @return the custEmail.
	 */
	@Column(name = "email", nullable = true, unique=true, length = 128)
	public String getEmail() {
		return email;
	}
	
	/**
	* @param custEmail the custEmail to set
	*/
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the firstName.
	 */
	@Column(name = "firstName", nullable = true, length = 128)
	public String getFirstName() {
		return firstName;
	}

	/**
	* @param firstName the firstName to set
	*/
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName.
	 */
	@Column(name = "lastName", nullable = true, length = 128)
	public String getLastName() {
		return lastName;
	}

	/**
	* @param lastName the lastName to set
	*/
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
    
    @Column(name = "mobileNumber", nullable = true, length = 128)
    public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "";
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
	public int compareTo(Object object) {
		// TODO Auto-generated method stub
		return 0;
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
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

}
