package com.urt.persistence.model.subscription;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.urt.persistence.model.base.PersistentObject;

@Entity
@Table(name="packageDetails")
public class PackageDetails extends PersistentObject{

	private static final long serialVersionUID = -8100441462952685691L;

	public PackageDetails() {
		
	}

   
    //private String packageName;
    private long studentsRange;
    private long costPerStudent;
    private long maxAllowableStudents;
    
    /* @Column(name = "packageName", nullable = true, length = 128)
    public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}*/

	@Column(name = "studentsRange", nullable = false, columnDefinition=" int default 0")
	public long getStudentsRange() {
		return studentsRange;
	}
	public void setStudentsRange(long studentsRange) {
		this.studentsRange = studentsRange;
	}

	@Column(name = "costPerStudent", nullable = false, columnDefinition=" int default 0")
	public long getCostPerStudent() {
		return costPerStudent;
	}

	public void setCostPerStudent(long costPerStudent) {
		this.costPerStudent = costPerStudent;
	}

	@Column(name = "maxAllowableStudents", nullable = false, columnDefinition=" int default 0")
	public long getMaxAllowableStudents() {
		return maxAllowableStudents;
	}

	public void setMaxAllowableStudents(long maxAllowableStudents) {
		this.maxAllowableStudents = maxAllowableStudents;
	}

	@Override
	public int compareTo(Object object) {
		/*PackageDetails target = (PackageDetails) object;
        if (target.getStudentsRange() != 0 && this.getStudentsRange()!= 0)
        {
           return this.getStudentsRange().compareToIgnoreCase(target.getStudentsRange());
        }*/
        return 0;
	}

	@Override
	public boolean equals(Object o) {
		return false;
	}

	@Override
	public int hashCode() {
		return 0;
	}

	@Override
	public String toString() {
		return "";
	}

}
