package com.urt.persistence.model.library;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.urt.persistence.model.base.PersistentObject;

/*
 * @create new table customer.
 */
@Entity
@Table(name = "bookLables")
public class BookLables  extends PersistentObject {
	
	private static final long serialVersionUID = 1L;

	/**
	 * @return the studentId
	 */
	public BookLables() {
    }
    public BookLables(long id) {
        setId(id);
    }

  
    private String bookLabelStatus;
    private String lableCode;
    private String type;
    private long custId;
    private String deleteStatus;
    private String description;
    private long deletedById;
    
 
    @Column(name = "bookLabelStatus", nullable = true, length = 2)
    public String getBookLabelStatus() {
		return bookLabelStatus;
	}
	public void setBookLabelStatus(String bookLabelStatus) {
		this.bookLabelStatus = bookLabelStatus;
	}
	@Column(name = "deleteStatus", nullable = true, length = 5,columnDefinition="varchar(5) default 'N'")
    public String getDeleteStatus() {
		return deleteStatus;
	}
	public void setDeleteStatus(String deleteStatus) {
		this.deleteStatus = deleteStatus;
	}
     
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Column(name = "deletedById", nullable = false ,columnDefinition="bigint(20) default 0")
	public long getDeletedById() {
		return deletedById;
	}
	public void setDeletedById(long deletedById) {
		this.deletedById = deletedById;
	}
	@Column(name = "custId", nullable = true, length = 20)
    public long getCustId() {
		return custId;
	}
	public void setCustId(long custId) {
		this.custId = custId;
	}
    /**
	 * @return the type
	 */
    @Column(name = "type", nullable = true, length = 2)
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	 
	 
	/**
	 * @return the lableCode
	 */
    @Column(name = "lableCode", nullable = true, length = 150)
	public String getLableCode() {
		return lableCode;
	}
	/**
	 * @param lableCode the lableCode to set
	 */
	public void setLableCode(String lableCode) {
		this.lableCode = lableCode;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
        .append("id", this.getId())
        .toString();
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
}
