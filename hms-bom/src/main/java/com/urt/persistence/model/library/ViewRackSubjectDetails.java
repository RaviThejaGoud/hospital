package com.urt.persistence.model.library;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

@Entity
@Table(name = "vw_racksubjectdetails")
public class ViewRackSubjectDetails implements Serializable,Cloneable,Comparable{

	public ViewRackSubjectDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	    private String rackName;
	    private String subjectName;
	    private String custId;
	    private String academicYearId;
	    private String noOfCopies;
		private String rackId;
	    private String subjectId;
	    private long Id;
	    private long bookTitleId;
	    
	    @Id
	    public long getId() {
			return Id;
		}
		public void setId(long id) {
			Id = id;
		}
		@Override
		public String toString() {
			return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
	        .append("id", this.getId())
	        .toString();
		}

		public String getRackName() {
			return rackName;
		}
		 public long getBookTitleId() {
				return bookTitleId;
			}
			public void setBookTitleId(long bookTitleId) {
				this.bookTitleId = bookTitleId;
			}
		public void setRackName(String rackName) {
			this.rackName = rackName;
		}
		public String getSubjectName() {
			return subjectName;
		}
		public void setSubjectName(String subjectName) {
			this.subjectName = subjectName;
		}
		public String getRackId() {
			return rackId;
		}


		public void setRackId(String rackId) {
			this.rackId = rackId;
		}


		public String getCustId() {
			return custId;
		}


		public void setCustId(String custId) {
			this.custId = custId;
		}


		public String getAcademicYearId() {
			return academicYearId;
		}


		public void setAcademicYearId(String academicYearId) {
			this.academicYearId = academicYearId;
		}


		public String getNoOfCopies() {
			return noOfCopies;
		}


		public void setNoOfCopies(String noOfCopies) {
			this.noOfCopies = noOfCopies;
		}

		public String getSubjectId() {
			return subjectId;
		}


		public void setSubjectId(String subjectId) {
			this.subjectId = subjectId;
		}


		public int compareTo(Object object) {
			// TODO Auto-generated method stub
			return 0;
		}

		 
		public boolean equals(Object o) {
			// TODO Auto-generated method stub
			return false;
		}

		 
		public int hashCode() {
			// TODO Auto-generated method stub
			return 0;
		}
		 
}
