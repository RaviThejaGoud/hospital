package com.urt.persistence.model.study;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.churchgroup.util.date.DateFunctions;
import com.churchgroup.util.object.ObjectFunctions;
import com.urt.persistence.model.base.PersistentObject;
import com.urt.persistence.model.common.StudyMaterialAttachments;

@Entity
@Table(name = "studyMaterials")
public class StudyMaterials  extends PersistentObject {
	
    private static final long serialVersionUID = 3832626162173359411L;
    private long custId;
    private long academicYearId;
    private long subjectId;
    private String materialName;
    private String description;
    protected Set<StudyClass> studyClasses;
    protected List<StudyMaterialAttachments> studyMaterialAttachments;
    
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "materialsStudyClasses",
       joinColumns = { @JoinColumn(name = "materialId") },
       inverseJoinColumns = { @JoinColumn(name = "studyClassId") })
    public Set<StudyClass> getStudyClasses() {
    	if(studyClasses == null)
        {
    		studyClasses = new HashSet<StudyClass>();
        }
		return studyClasses;
	}

	public void setStudyClasses(Set<StudyClass> studyClasses) {
		this.studyClasses = studyClasses;
	}
    public void addNewStudyClasses(List<StudyClass> studyClasses) {
    	getStudyClasses().addAll(studyClasses);
    }

	public String getMaterialName() {
		return materialName;
	}
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(long subjectId) {
		this.subjectId = subjectId;
	}

   
	public long getCustId() {
		return custId;
	}

	public void setCustId(long custId) {
		this.custId = custId;
	}

	public long getAcademicYearId() {
		return academicYearId;
	}

	public void setAcademicYearId(long academicYearId) {
		this.academicYearId = academicYearId;
	}

	public StudyMaterials() {
        this.createdDate=DateFunctions.getTodayPlusNdays(0);
        this.lastUpdatedDate=DateFunctions.getTodayPlusNdays(0);
        this.lastAccessDate=DateFunctions.getTodayPlusNdays(0);
    }

    public StudyMaterials(long id) {
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
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="studyMaterialsId")
	public List<StudyMaterialAttachments> getStudyMaterialAttachments() {
		if(ObjectFunctions.isNullOrEmpty(this.studyMaterialAttachments))
		{
			this.studyMaterialAttachments = new ArrayList();
		}
		return studyMaterialAttachments;
		
	}

	public void setStudyMaterialAttachments(
			List<StudyMaterialAttachments> studyMaterialAttachments) {
		this.studyMaterialAttachments = studyMaterialAttachments;
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
    

  

