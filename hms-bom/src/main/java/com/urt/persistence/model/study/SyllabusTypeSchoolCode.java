package com.urt.persistence.model.study;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.urt.persistence.model.base.PersistentObject;

@Entity
@Table(name = "syllabusTypeSchoolCode")
public class SyllabusTypeSchoolCode  extends PersistentObject {
	
    private static final long serialVersionUID = 3832626162173359411L;
    
    protected String schoolCode;
    protected String type;  //P = primary and H = Higher
    protected long custId;
    private SyllabusType syllabusType;
  

    public SyllabusTypeSchoolCode() {
		super();
	}
   

	public String getSchoolCode() {
		return schoolCode;
	}

	public void setSchoolCode(String schoolCode) {
		this.schoolCode = schoolCode;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public long getCustId() {
		return custId;
	}

	public void setCustId(long custId) {
		this.custId = custId;
	}

	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="syllabusTypeId", insertable=true, updatable=true)
	public SyllabusType getSyllabusType() {
		return syllabusType;
	}

	public void setSyllabusType(SyllabusType syllabusType) {
		this.syllabusType = syllabusType;
	}

	@Override
	public int compareTo(Object object) {
    	SyllabusType target = (SyllabusType) object;
    	if (target != null && this != null)
        {
    		if(this.getId() >= target.getId())
    			return 1;
    		else
    			return 0;
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

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "";
	}

}
    