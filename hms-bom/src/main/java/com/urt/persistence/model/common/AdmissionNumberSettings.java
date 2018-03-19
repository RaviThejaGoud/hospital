package com.urt.persistence.model.common;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.churchgroup.util.date.DateFormatter;
import com.churchgroup.util.object.ObjectFunctions;
import com.urt.persistence.model.base.PersistentObject;
import com.urt.persistence.model.study.ClassName;

/*
 * @create new table customer.
 */
@Entity
@Table(name = "admissionNumberSettings")
public class AdmissionNumberSettings  extends PersistentObject {
	
    private static final long serialVersionUID = 3832626162173359411L;
    
    
    private long custId;
    private String prefix;
    private String postfix;
    private AcademicYear academicYear;
    private int sequenceNumber;
    private long admissionSettingsId;
    private Set<ClassName> classNameClassId;
    private String admissionNumberType; //SW - School Wide,  CW - Class Wide
    
    
    
    
    
    @Column(name = "admissionNumberType",nullable=true, length = 2,columnDefinition="char(2) default 'SW'")
    public String getAdmissionNumberType() {
		return admissionNumberType;
	}

	public void setAdmissionNumberType(String admissionNumberType) {
		this.admissionNumberType = admissionNumberType;
	}

	public long getAdmissionSettingsId() {
		return admissionSettingsId;
	}

	public void setAdmissionSettingsId(long admissionSettingsId) {
		this.admissionSettingsId = admissionSettingsId;
	}

	@ManyToMany(cascade = CascadeType.ALL,fetch=FetchType.EAGER)
    @JoinTable(name = "admissionNumberClasses",
       joinColumns = { @JoinColumn(name = "admissionNumberSettingsId") },
       inverseJoinColumns = { @JoinColumn(name = "classId") })
    
	public Set<ClassName> getClassNameClassId() {
    	if(classNameClassId == null)
        {
    		classNameClassId = new HashSet<ClassName>();
        }
		return classNameClassId;
	}

	public void setClassNameClassId(Set<ClassName> classNameClassId) {
		this.classNameClassId = classNameClassId;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getPostfix() {
		return postfix;
	}

	public void setPostfix(String postfix) {
		this.postfix = postfix;
	}

	@Column(name = "sequenceNumber",nullable = false, columnDefinition=" int default 0")
	public int getSequenceNumber() {
		return sequenceNumber;
	}

	public void setSequenceNumber(int sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

	@OneToOne
    @JoinColumn(name="academicYearId")
    public AcademicYear getAcademicYear() {
		return academicYear;
	}

	public void setAcademicYear(AcademicYear academicYear) {
		this.academicYear = academicYear;
	}

    
	public AdmissionNumberSettings() {
        
    }

	public AdmissionNumberSettings(long id) {
        setId(id);
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

	
	/**
	 * @return the custId
	 */
	@Column(name = "custId", nullable = true, length = 10)
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
	public String getCreatedDateStr() {
		return DateFormatter.formatDate(DateFormatter.DDMMCCYY_PATTERN, this.getCreatedDate());
	}
	/*@Transient
	public String getApplicationStartDateStr() {
		return DateFormatter.formatDate(DateFormatter.MM_DD_YYYY_PATTERN1, this.getStartDate());
	}
	@Transient
	public String getApplicationClosedDateStr() {
		return DateFormatter.formatDate(DateFormatter.MM_DD_YYYY_PATTERN1, this.getEndDate());
	}
	@Transient
	public String getAdmissionsEndDateStr() {
		return DateFormatter.formatDate(DateFormatter.MM_DD_YYYY_PATTERN1, this.getAdmissionEndDate());
	}
	@Transient
	public String getApplicationsStartDateStr() {
		return DateFormatter.formatDate(DateFormatter.ddMMMyyyy_PATTERN1, this.getStartDate());
	}
	@Transient
	public String getApplicationsClosedDateStr() {
		return DateFormatter.formatDate(DateFormatter.ddMMMyyyy_PATTERN1, this.getEndDate());
	}*/


	
	
	@Transient
	public long getAcademicYearId(){
		if(ObjectFunctions.isNullOrEmpty(getAcademicYear()))
			return 0;
		else
			return getAcademicYear().getId();
	}
	
	@Transient
	public void addNewClass(ClassName className) {
        getClassNameClassId().add(className);
    }
	
}
    

  

