package com.urt.persistence.model.exam;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.urt.persistence.model.base.PersistentObject;
import com.urt.persistence.model.common.AcademicYear;

/*
 * @create new table Syllabus.
 */
@Entity
@Table(name = "syllabus")
public class Syllabus  extends PersistentObject {
	
    private static final long serialVersionUID = 3832626162173359411L;
   
//  protected long classSectionId;
    protected String chapterName;
    protected String contentName;
	protected long subjectId;
	protected String bookTitle;
	protected String authorName;
	protected String referenceName;
	protected String publisherName;
	private AcademicYear academicYear;
    protected long custId;
	
	public Syllabus() {
        
    }
     
    
    public Syllabus(long id) {
        setId(id);
    }
    
    public long getCustId() {
		return custId;
	}
	public void setCustId(long custId) {
		this.custId = custId;
	}

	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="academicYearId") 
	public AcademicYear getAcademicYear() {
		return academicYear;
	}
	public void setAcademicYear(AcademicYear academicYear) {
		this.academicYear = academicYear;
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
        .append("classId",this.getChapterName()).toString();
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
	/*@Column(name="classSectionId", nullable=false,  columnDefinition=" int default 0")
	public long getClassSectionId() {
		return classSectionId;
	}
	public void setClassSectionId(long classSectionId) {
		this.classSectionId = classSectionId;
	}*/
	
	@Column(name="chapterName", nullable=true, length = 255)
	public String getChapterName() {
		return chapterName;
	}

	public void setChapterName(String chapterName) {
		this.chapterName = chapterName;
	}
	
	@Column(name="subjectId", nullable=true, length = 10)
	public long getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(long subjectId) {
		this.subjectId = subjectId;
	}
	/**
	 * @return the contentName
	 */
	@Column(name="contentName", nullable=true, length = 1024)
	public String getContentName() {
		return contentName;
	}


	/**
	 * @param contentName the contentName to set
	 */
	public void setContentName(String contentName) {
		this.contentName = contentName;
	}
	/**
	 * @return the bookTitle
	 */
	@Column(name="bookTitle", nullable=true, length = 1024)
	public String getBookTitle() {
		return bookTitle;
	}


	/**
	 * @param bookTitle the bookTitle to set
	 */
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}


	/**
	 * @return the autherName
	 */
	@Column(name="authorName", nullable=true, length = 1024)
	public String getAutherName() {
		return authorName;
	}


	/**
	 * @param autherName the autherName to set
	 */
	public void setAutherName(String authorName) {
		this.authorName = authorName;
	}


	/**
	 * @return the referanceName
	 */
	@Column(name="referenceName", nullable=true, length = 1024)
	public String getReferenceName() {
		return referenceName;
	}


	/**
	 * @param referanceName the referanceName to set
	 */
	public void setReferenceName(String referenceName) {
		this.referenceName = referenceName;
	}
	/**
	 * @return the publisherName
	 */
	@Column(name="publisherName", nullable=true, length = 1024)
	public String getPublisherName() {
		return publisherName;
	}


	/**
	 * @param publisherName the publisherName to set
	 */
	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}
	public void copyFrom(Syllabus obj)
    {
		 setAutherName(obj.getAutherName());
		 setBookTitle(obj.getBookTitle());
		 setChapterName(obj.getChapterName());
		 setContentName(obj.getContentName());
		 setCreatedDate(new Date());
		 setCustId(obj.getCustId());
		 setLastAccessDate(new Date());
		 setLastUpdatedDate(new Date());
		 setPublisherName(obj.getPublisherName());
		 setReferenceName(obj.getReferenceName());
		 //setVersion(0);
    }
}

