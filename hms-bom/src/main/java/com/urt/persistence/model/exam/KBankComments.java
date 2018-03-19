package com.urt.persistence.model.exam;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.churchgroup.util.date.DateFormatter;
import com.urt.persistence.model.base.PersistentObject;
import com.urt.persistence.model.user.User;

/*
 * @create new table Syllabus.
 */
/**
 * @author urt
 *
 */
@Entity
@Table(name = "kBankComments")
public class KBankComments  extends PersistentObject {
	
    private static final long serialVersionUID = 3832626162173359411L;
   
    private String description;
    protected User commentAccount;
    private long kBankId;
	
	public KBankComments() {
        
    }
    public KBankComments(long id) {
        setId(id);
    }
    
    @Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
        .append("id", this.getId())
        .append("description", this.description).toString();
  
	}
    @Override
	public int compareTo(Object object) {
    	KBankComments target = (KBankComments) object;		 
        if (target.getDescription() != null && this.getDescription() != null)
        {
            if(this.getDescription().equalsIgnoreCase(target.getDescription()))
                return 0;
            else 
               return target.getDescription().compareToIgnoreCase(this.getDescription());
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
	@Column(name="description", nullable=true, length = 1024)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Column(name="kBankId", nullable=true, length = 10)
	public long getkBankId() {
		return kBankId;
	}
	public void setkBankId(long kBankId) {
		this.kBankId = kBankId;
	}
	/*@Column(name="userId", nullable=true, length = 10)
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	*/
	@ManyToOne
    @JoinColumn(name = "accountId", referencedColumnName = "id")
	public User getCommentAccount() {
		return commentAccount;
	}
	public void setCommentAccount(User commentAccount) {
		this.commentAccount = commentAccount;
	}
	@Transient
	public String getCreatedDateStr() {
		try{
			return DateFormatter.formatDate(DateFormatter.MMMMDDCCYY_PATTERN, this.getCreatedDate());
		}
		catch(Exception ex){
			return "";
		}
	}
}

