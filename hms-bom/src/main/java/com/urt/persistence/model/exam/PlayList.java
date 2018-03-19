package com.urt.persistence.model.exam;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.churchgroup.util.object.ObjectFunctions;
import com.urt.persistence.model.base.PersistentObject;

@Entity
@Table(name = "playList")
public class PlayList extends PersistentObject  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String apiUrl;
	private String youtubeUrl;
	private String title;
	private String description;
	private String youtubeId;
	private String subjectName;
	private String subTopic;
	
	protected Set<PlayListVideo> playListVideo;
	
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
 	@JoinColumn(name="playListId")
	public Set<PlayListVideo> getPlayListVideo() {
		return this.playListVideo;
	}
	
	public void setPlayListVideo(Set<PlayListVideo> playListVideo) {
		this.playListVideo = playListVideo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	@Column(name="description", nullable=true, length = 20258)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getApiUrl() {
		return apiUrl;
	}

	public void setApiUrl(String apiUrl) {
		this.apiUrl = apiUrl;
	}

	public String getYoutubeUrl() {
		return youtubeUrl;
	}

	public void setYoutubeUrl(String youtubeUrl) {
		this.youtubeUrl = youtubeUrl;
	}

	public String getYoutubeId() {
		return youtubeId;
	}

	public void setYoutubeId(String youtubeId) {
		this.youtubeId = youtubeId;
	}

	/**
	 * @return the subjectName
	 */
	public String getSubjectName() {
		return subjectName;
	}

	/**
	 * @param subjectName the subjectName to set
	 */
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	/**
	 * @return the subTopic
	 */
	public String getSubTopic() {
		return subTopic;
	}

	/**
	 * @param subTopic the subTopic to set
	 */
	public void setSubTopic(String subTopic) {
		this.subTopic = subTopic;
	}

	public void addPlayListVideo(PlayListVideo playListVideo) {
		if(ObjectFunctions.isNullOrEmpty(this.getPlayListVideo())){
			this.playListVideo=new HashSet<PlayListVideo>();
		}
		this.playListVideo.add(playListVideo);
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
