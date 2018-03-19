package com.urt.persistence.model.study;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.annotations.Type;

import com.churchgroup.util.date.DateFunctions;
import com.churchgroup.util.string.StringFunctions;
import com.urt.persistence.model.base.PersistentObject;

@Entity
@Table(name = "generalSubjects")
public class GeneralSubjects  extends PersistentObject {
    private static final long serialVersionUID = 3832626162173359411L;
    protected String name;                  
    protected String description; 
    protected String subjectNumber;    
    private boolean language;
    protected String subjectType;  
    private long syllabusTypeId;
    
    public long getSyllabusTypeId() {
		return syllabusTypeId;
	}
	public void setSyllabusTypeId(long syllabusTypeId) {
		this.syllabusTypeId = syllabusTypeId;
	}
	@Column(name = "subjectType", nullable = true, length = 2)      
    public String getSubjectType() {
		return subjectType;
	}
	public void setSubjectType(String subjectType) {
		this.subjectType = subjectType;
	}
	
	/**
	 * @return the language
	 */
    @Column(name = "language", nullable = true, length = 1,columnDefinition="char(1) default 'N'")
    @Type(type="yes_no")
	public boolean isLanguage() {
		return language;
	}
	/**
	 * @param language the language to set
	 */
	public void setLanguage(boolean language) {
		this.language = language;
	}
    /**
	 * @return the subjectNumber
	 */
	public String getSubjectNumber() {
		return subjectNumber;
	}
	/**
	 * @param subjectNumber the subjectNumber to set
	 */
	public void setSubjectNumber(String subjectNumber) {
		this.subjectNumber = subjectNumber;
	}
    public GeneralSubjects() {
        this.createdDate=DateFunctions.getTodayPlusNdays(0);
        this.lastUpdatedDate=DateFunctions.getTodayPlusNdays(0);
        this.lastAccessDate=DateFunctions.getTodayPlusNdays(0);
    }

    public GeneralSubjects(long id) {
        setId(id);
    }

	@Override
	public int compareTo(Object object) {
		GeneralSubjects sub=(GeneralSubjects)object;
		if(StringFunctions.isNotNullOrEmpty(sub.getSubjectNumber()) && StringFunctions.isNotNullOrEmpty(this.getSubjectNumber())){
			return this.getSubjectNumber().compareToIgnoreCase(sub.getSubjectNumber());
		}else{
			return this.getName().compareToIgnoreCase(sub.getName());			
		}
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
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
        .append("id", this.getId())
        .append("name", this.name).toString();
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	public void copyFrom(GeneralSubjects subject)
    {
		 	setName(subject.getName());                  
		    setDescription(subject.getDescription()); 
    }
}
    
