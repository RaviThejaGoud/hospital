package com.urt.persistence.model.common;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.CompareToBuilder;

import com.churchgroup.util.object.ObjectFunctions;
import com.urt.persistence.model.base.PersistentObject;
import com.urt.persistence.model.study.ClassName;

/*
 * @create new table Syllabus.
 */
/**
 * @author urt
 *
 */
@Entity
@Table(name = "tcBookSettings")
public class TcBookSettings  extends PersistentObject {
    private static final long serialVersionUID = 3832626162173359411L;
	
    
 
    private long custId;
    private int tcBookNumber;
    private int tcStartingNumber;
    private int tcEndingNumber;
    private Set<ClassName> classes;
    private long academicYearId;
    private String type;
    
	 
    
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
	@ManyToMany
    @JoinTable(name = "tcBookSettingsClasses",
       joinColumns = { @JoinColumn(name = "tcBookId") },
       inverseJoinColumns = { @JoinColumn(name = "classId") })
    public Set<ClassName> getClasses() {
		return classes;
	}
	public void setClasses(Set<ClassName> classes) {
		this.classes = classes;
	}
	@Column(name = "academicYearId", columnDefinition="int default 0")
	public long getAcademicYearId() {
		return academicYearId;
	}
	public void setAcademicYearId(long academicYearId) {
		this.academicYearId = academicYearId;
	}
	@Column(name = "tcBookNumber", nullable = false)
	public int getTcBookNumber() {
		return tcBookNumber;
	}
	public void setTcBookNumber(int tcBookNumber) {
		this.tcBookNumber = tcBookNumber;
	}
	@Column(name = "tcStartingNumber", nullable = false)
	public int getTcStartingNumber() {
		return tcStartingNumber;
	}
	public void setTcStartingNumber(int tcStartingNumber) {
		this.tcStartingNumber = tcStartingNumber;
	}
	@Column(name = "tcEndingNumber", nullable = false)
	public int getTcEndingNumber() {
		return tcEndingNumber;
	}
	public void setTcEndingNumber(int tcEndingNumber) {
		this.tcEndingNumber = tcEndingNumber;
	}
	public TcBookSettings() {
        
    }
    public TcBookSettings(long id) {
        setId(id);
    }

	public long getCustId() {
		return custId;
	}

	public void setCustId(long custId) {
		this.custId = custId;
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
	@Override
	public int compareTo(Object object) {
		TcBookSettings days = (TcBookSettings) object;
        return new CompareToBuilder().append(this.getId(), days.getId()).toComparison();
	}
	
	@Transient
	public String getBookSettingsClasses(){
		if(ObjectFunctions.isNullOrEmpty(this.classes)){
			return "";
		}else{
			StringBuffer classNames = new StringBuffer();
			for(ClassName clasName : this.classes){
				if(classNames.length() > 0)
					classNames.append(", ");
				classNames.append(clasName.getClassName());
			}
			return classNames.toString();
		}
	}
}

