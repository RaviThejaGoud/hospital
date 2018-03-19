package com.urt.persistence.model.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.churchgroup.util.date.DateFormatter;
import com.urt.persistence.model.base.PersistentObject;

@Entity
@Table(name = "schoolVideos")
public class SchoolVideos extends PersistentObject  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String title;
	private String youtubeId;
	private String embedCode;
	private String receiverType;	
	private long custId;
	private long academicYearId;
	 

	 
	public String getReceiverType() {
		return receiverType;
	}

	public void setReceiverType(String receiverType) {
		this.receiverType = receiverType;
	}

	@Column(name="embedCode", nullable=true, length = 20258)
	public String getEmbedCode() {
		return embedCode;
	}

	public void setEmbedCode(String embedCode) {
		this.embedCode = embedCode;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getYoutubeId() {
		return youtubeId;
	}

	public void setYoutubeId(String youtubeId) {
		this.youtubeId = youtubeId;
	}

	 @Override
		public String toString() {
			return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
	        .append("id", this.getId())
	        .append("title", this.title).toString();
	  
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
		@Transient
		public String getCreatedDateStr() {
			try {
				return DateFormatter.formatDate(DateFormatter.MM_DD_YYYY_PATTERN1 , this.getCreatedDate());
			} catch (Exception ex) {
				return "";
			}
		}
}
