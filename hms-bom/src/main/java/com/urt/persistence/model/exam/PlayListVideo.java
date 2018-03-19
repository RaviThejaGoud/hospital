package com.urt.persistence.model.exam;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.urt.persistence.model.base.PersistentObject;

@Entity
@Table(name = "playListVideo")
public class PlayListVideo extends PersistentObject  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String videoPosition;
	private String description;
	private String title;
	private String kaUrl;
	private String youtubeId;
	private String youtubeUrl;
	private String keywords;
	private String readableId;
	private long subjectName;
	private String url;
	
	
	public String getYoutubeUrl() {
		return youtubeUrl;
	}
	public void setYoutubeUrl(String youtubeUrl) {
		this.youtubeUrl = youtubeUrl;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Column(name="description", nullable=true, length = 20258)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public String getVideoPosition() {
		return videoPosition;
	}
	public void setVideoPosition(String videoPosition) {
		this.videoPosition = videoPosition;
	}
	public String getKaUrl() {
		return kaUrl;
	}
	public void setKaUrl(String kaUrl) {
		this.kaUrl = kaUrl;
	}
	public String getYoutubeId() {
		return youtubeId;
	}
	public void setYoutubeId(String youtubeId) {
		this.youtubeId = youtubeId;
	}
	public String getReadableId() {
		return readableId;
	}
	public void setReadableId(String readableId) {
		this.readableId = readableId;
	}
	
	 /**
	 * @return the subjectName
	 */
	public long getSubjectName() {
		return subjectName;
	}
	/**
	 * @param subjectName the subjectName to set
	 */
	public void setSubjectName(long subjectName) {
		this.subjectName = subjectName;
	}
	@Override
		public String toString() {
			return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
	        .append("id", this.getId())
	        .append("title", this.title)
	        .append("description", this.description).toString();
	  
		}
	    @Override
		public int compareTo(Object object) {
	    	PlayListVideo target = (PlayListVideo) object;		 
	        if (target.getTitle() != null && this.getTitle() != null)
	        {
	            if(this.getTitle().equalsIgnoreCase(target.getTitle()))
	                return 0;
	            else 
	               return target.getTitle().compareToIgnoreCase(this.getTitle());
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
}
