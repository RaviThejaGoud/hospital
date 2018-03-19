package com.urt.persistence.model.event;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.churchgroup.util.date.DateFormatter;
import com.churchgroup.util.date.DateUtil;

/**
 * This class is used to represent available roles in the database.</p>
 *
 * <p><a href="Role.java.html"><i>View Source</i></a></p>
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 *  Version by Dan Kibler dan@getrolling.com
 *  Extended to implement Acegi GrantedAuthority interface 
 *  	by David Carter david@carter.net
 *
 * @struts.form extends="BaseForm"
 * @hibernate.class table="role"
 */
public class RssEvent implements Comparable{
    
    private static final long serialVersionUID = 3690197650654049848L;
    
    private String title;
    private String description;
    private String summary;
    private Date startDate;
    private Date endDate;
    /**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}
	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}
	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	private String location;

    public RssEvent() {
        super();
        // TODO Auto-generated constructor stub
    }
    /**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	public RssEvent(String title, String description, String summary,
			Date startDate, Date endDate, String location) {
		super();
		this.title = title;
		this.description = description;
		this.summary = summary;
		this.startDate = startDate;
		this.endDate = endDate;
		this.location = location;
	}



	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}





	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}





	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}





	/**
	 * @return the summary
	 */
	public String getSummary() {
		return summary;
	}

	/**
	 * @param summary the summary to set
	 */
	public void setSummary(String summary) {
		this.summary = summary;
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RssEvent)) return false;

        final RssEvent rssEvent = (RssEvent) o;

        return !(title != null ? !title.equals(rssEvent.getTitle()) : rssEvent.title != null);

    }

    public int hashCode() {
        return (title != null ? title.hashCode() : 0);
    }

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE)
                .append(" title: ")
                .append(this.getTitle())
                .append(" description : ")
                .append(this.getDescription())
                .append(" start date : ")
                .append(this.getStartDate())               
                .toString();
    }
    /**
     * @see java.lang.Comparable#compareTo(Object)
     */
    public int compareTo(Object object) {       
        RssEvent target = (RssEvent) object;
        long timeDifference = 0;
        if (target.getStartDate()!= null && this.getStartDate()!= null)
        {
            timeDifference = this.getStartDate().getTime()
                    - target.getStartDate().getTime();
        }
        int difference;
        if(timeDifference == 0)
        {
            difference = 0;
        }
        else if(timeDifference < 0)
        {
            difference = -1;
        }
        else
        {
            difference = 1;
        }
        return difference;        
    }
    
    public String getDtstart(){
    	return DateUtil.prepareRSSDateFormat(this.getStartDate());
    }
	
    public String getDtend(){
    	return DateUtil.prepareRSSDateFormat(this.getEndDate());
    }
    public String getTitleWithDate(){
    	try{
    	   return getTitle()+" On " +DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_HHMMSS_PATTERN, this.getStartDate());
   
    	}
    	catch (Exception e) {
			// TODO: handle exception
    		return getTitle();
		}
    }

}
