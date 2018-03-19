package com.urt.persistence.model.event;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.churchgroup.util.date.DateFormatter;
import com.churchgroup.util.object.ObjectFunctions;
import com.urt.persistence.model.base.PersistentObject;
 

/*
 * @create new table recurringEvents.
 */
@Entity
@Table(name = "eventsAlbum")
public class EventsAlbum  extends PersistentObject {
	
    private static final long serialVersionUID = 3832626162173359411L;
    
    protected long custId;
    protected String albumName;
    protected String status;
    protected long academicYearId;
    protected long eventId;
    protected List<AlbumAttachment> attachmentList;
    protected List albumAttachmentList;
    protected AlbumAttachment albumAtt;
    protected Set<AlbumAttachment> albumAttachment;
    
    
	@ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
    @JoinTable(name = "eventAndAlbum",
       joinColumns = { @JoinColumn(name = "eventAlbumId") },
       inverseJoinColumns = { @JoinColumn(name = "albumAttachmentId") })
   // @javax.xml.bind.annotation.XmlTransient()
    public Set<AlbumAttachment> getAlbumAttachment() {
        if(albumAttachment == null)
        {
        	albumAttachment = new HashSet<AlbumAttachment>();
        }
        return this.albumAttachment;
    }
	
	public void setAlbumAttachment(Set<AlbumAttachment> albumAttachment) {
		this.albumAttachment = albumAttachment;
	}
	
	
	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="imageId", insertable=true, updatable=true) 
	public AlbumAttachment getAlbumAtt() {
		return albumAtt;
	}

	public void setAlbumAtt(AlbumAttachment albumAtt) {
		this.albumAtt = albumAtt;
	}
	
	public long getEventId() {
		return eventId;
	}

	public void setEventId(long eventId) {
		this.eventId = eventId;
	}

	public long getCustId() {
		return custId;
	}

	public void setCustId(long custId) {
		this.custId = custId;
	}

	public String getAlbumName() {
		return albumName;
	}

	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}

	public long getAcademicYearId() {
		return academicYearId;
	}

	public void setAcademicYearId(long academicYearId) {
		this.academicYearId = academicYearId;
	}
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
 	@JoinColumn(name="albumAttachmentId") 	
	public List<AlbumAttachment> getAttachmentList() {
		return attachmentList;
	}

	public void setAttachmentList(List<AlbumAttachment> attachmentList) {
		this.attachmentList = attachmentList;
	}
	
	 public void addAttachmentFiles(AlbumAttachment attachment) {
			if(ObjectFunctions.isNullOrEmpty(this.getAttachmentList())){
				this.attachmentList=new ArrayList<AlbumAttachment>();
			}
			this.attachmentList.add(attachment);
		}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", this.getId())
                .append("albumName",this.albumName).toString();
        
    }

    /**
     * @see java.lang.Comparable#compareTo(Object)
     */
        @Override
        public int compareTo(Object object) {
            return 0;
        }
        public boolean equals(Object o) {
               return true;
        }

        public int hashCode() {
          return 0;
        }

		@Transient
		public String getEventStartDateStr() {
	        return DateFormatter.formatDate(DateFormatter.ddMMMyyyy_PATTERN1,this.getCreatedDate());
	    }
		
		@Transient
		public List getAlbumAttachmentList() {
			if(ObjectFunctions.isNullOrEmpty(this.albumAttachmentList)){
				this.albumAttachmentList=new ArrayList();
			}
			return this.albumAttachmentList;
		}

		public void setAlbumAttachmentList(List albumAttachmentList) {
			this.albumAttachmentList = albumAttachmentList;
		}
		 
}