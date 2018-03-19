package com.urt.persistence.model.exam;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

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
@Table(name = "kBankRating")
public class KBankRating  extends PersistentObject {
	
    private static final long serialVersionUID = 3832626162173359411L;
   
    private User createrAccount;
    private User logAccount;
    private Integer totalVotes;
    private Integer totalValue;
    private float average;
    private long kBankId;
	
	
	public KBankRating() {
        
    }
    public KBankRating(long id) {
        setId(id);
    }
    
    @Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
        .append("id", this.getId())
        .append("average", this.average).toString();
  
	}
    @Override
    public int compareTo(Object object)
    {
    	KBankRating target = (KBankRating) object;
        if(this.totalValue > target.totalValue)
                return -1;
        else if(this.totalValue < target.totalValue)
               return 1;
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
	@ManyToOne
    @JoinColumn(name = "creterAccountId", referencedColumnName = "id")
	public User getCreaterAccount() {
		return createrAccount;
	}
	public void setCreaterAccount(User createrAccount) {
		this.createrAccount = createrAccount;
	}
	@ManyToOne
    @JoinColumn(name = "loginAccountId", referencedColumnName = "id")
	public User getLogAccount() {
		return logAccount;
	}
	public void setLogAccount(User logAccount) {
		this.logAccount = logAccount;
	}
	
	@Column(name="totalVotes", nullable=true, length = 10)
	public Integer getTotalVotes() {
		return totalVotes;
	}
	public void setTotalVotes(Integer totalVotes) {
		this.totalVotes = totalVotes;
	}
	@Column(name="totalValue", nullable=true, length = 10)
	public Integer getTotalValue() {
		return totalValue;
	}
	public void setTotalValue(Integer totalValue) {
		this.totalValue = totalValue;
	}
	@Column(name="average", nullable=true, length = 10)
	public float getAverage() {
		return average;
	}
	public void setAverage(float average) {
		this.average = average;
	}
	
	@Column(name="kBankId", nullable=true, length = 10)
	public long getkBankId() {
		return kBankId;
	}
	public void setkBankId(long kBankId) {
		this.kBankId = kBankId;
	}
	@Transient
	public int getIntAverage()
    {
    	return (int)getAverage();
    }
	
	
}

