package com.urt.persistence.model.library;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

@Entity
@Table(name = "vw_bookrackassignmentdetails")
public class ViewBookRackAssignmentDetails implements Serializable,Cloneable,Comparable{

	public ViewBookRackAssignmentDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	    private String bookName;
	    private String bookTitleId;
	    private String rackId;
	    private String custId;
	    private String academicYearId;
	    private String noOfCopies;
	    private String noOfCount; 
	    private String existedCount;
	    private String subjectId;
		
		@Override
		public String toString() {
			return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
	        .append("id", this.getBookTitleId())
	        .toString();
		}
		 
		public String getBookName() {
			return bookName;
		}


		public void setBookName(String bookName) {
			this.bookName = bookName;
		}

		@Id
		public String getBookTitleId() {
			return bookTitleId;
		}


		public void setBookTitleId(String bookTitleId) {
			this.bookTitleId = bookTitleId;
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


		public String getNoOfCount() {
			return noOfCount;
		}


		public void setNoOfCount(String noOfCount) {
			this.noOfCount = noOfCount;
		}


		public String getExistedCount() {
			return existedCount;
		}


		public void setExistedCount(String existedCount) {
			this.existedCount = existedCount;
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
