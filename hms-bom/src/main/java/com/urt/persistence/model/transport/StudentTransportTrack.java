package com.urt.persistence.model.transport;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.urt.persistence.model.base.PersistentObject;
import com.urt.persistence.model.common.AcademicYear;
import com.urt.persistence.model.study.Student;
import com.urt.persistence.model.user.User;

@Entity
@Table(name = "studentTransportTrack")
public class StudentTransportTrack extends PersistentObject {

	private static final long serialVersionUID = 3832626162173359411L;

	private long custId;
	private AcademicYear academicYear;
	private Student student;
	private Date startDate;
	private Date endDate;

	public StudentTransportTrack() {
		this.createdDate = new Date();
		this.lastAccessDate = new Date();
		this.lastUpdatedDate = new Date();
	}

	public StudentTransportTrack(long id) {
		setId(id);
	}

	@Override
	public int compareTo(Object object) {
		StudentTransportTrack myClass = (StudentTransportTrack) object;
		return new CompareToBuilder()
				.append(this.getCreatedDate(), myClass.getCreatedDate())
				.append(this.getCreatedById(), myClass.getCreatedById())
				.toComparison();
	}

	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof User))
			return false;

		return true;
	}

	public int hashCode() {
		return (getStrId() != null ? this.getStrId().hashCode() : 0);
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
				.append("id", this.getId()).toString();
	}

	@Column(name = "custId", nullable = false, length = 10)
	public long getCustId() {
		return custId;
	}

	public void setCustId(long custId) {
		this.custId = custId;
	}

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "academicYearId", insertable = true, updatable = true)
	public AcademicYear getAcademicYear() {
		return academicYear;
	}

	public void setAcademicYear(AcademicYear academicYear) {
		this.academicYear = academicYear;
	}

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "studentId", insertable = true, updatable = true)
	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
}