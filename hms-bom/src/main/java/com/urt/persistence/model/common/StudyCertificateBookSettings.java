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
@Table(name = "studyBookSettings")
public class StudyCertificateBookSettings  extends PersistentObject {
    private static final long serialVersionUID = 3832626162173359411L;
	
    private long custId;
    private int studyBookNumber;
    private int studyStartingNumber;
    private int studyEndingNumber;
    private Set<ClassName> classes;
    private long academicYearId;
    //private String bookSettingType;
	 
    
	/*public String getBookSettingType() {
		return bookSettingType;
	}
	public void setBookSettingType(String bookSettingType) {
		this.bookSettingType = bookSettingType;
	}*/
	
	@ManyToMany
    @JoinTable(name = "studyBookSettingsClasses",
       joinColumns = { @JoinColumn(name = "studyBookId") },
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
	@Column(name = "studyBookNumber", nullable = false)
	public int getStudyBookNumber() {
		return studyBookNumber;
	}
	public void setStudyBookNumber(int studyBookNumber) {
		this.studyBookNumber = studyBookNumber;
	}
	@Column(name = "studyStartingNumber", nullable = false)
	public int getStudyStartingNumber() {
		return studyStartingNumber;
	}
	
	public void setStudyStartingNumber(int studyStartingNumber) {
		this.studyStartingNumber = studyStartingNumber;
	}
	@Column(name = "studyEndingNumber", nullable = false)
	public int getStudyEndingNumber() {
		return studyEndingNumber;
	}
	
	public void setStudyEndingNumber(int studyEndingNumber) {
		this.studyEndingNumber = studyEndingNumber;
	}
	
	public StudyCertificateBookSettings() {
        
    }
    
	public StudyCertificateBookSettings(long id) {
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
		StudyCertificateBookSettings days = (StudyCertificateBookSettings) object;
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

