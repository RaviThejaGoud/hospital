package com.urt.persistence.model.jrexception;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.urt.persistence.model.base.PersistentObject;


@Entity
@Table(name = "jrExceptionDetails")
public class JRExceptionDetails extends PersistentObject {
  
	private static final long serialVersionUID = -7011916019269690369L;
	
	private String exceptionDescription;
    private JRException jrException;

    
	/**
	 * @return the exceptionDescription
	 */
    @Column(name = "exceptionDescription", columnDefinition="varchar(30720)")
	public String getExceptionDescription() {
		return exceptionDescription;
	}

	/**
	 * @param exceptionDescription the exceptionDescription to set
	 */
	public void setExceptionDescription(String exceptionDescription) {
		this.exceptionDescription = exceptionDescription;
	}

	/**
	 * @return the jrException
	 */
	@OneToOne
	@JoinColumn(name="jrExceptionId") 
	public JRException getJrException() {
		return jrException;
	}

	/**
	 * @param jrException the jrException to set
	 */
	public void setJrException(JRException jrException) {
		this.jrException = jrException;
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
}