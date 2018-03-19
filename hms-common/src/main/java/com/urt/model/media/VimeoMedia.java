package com.urt.model.media;

public class VimeoMedia {
    private String id;
    private String title;
    private String description;
    private String url;
    private String upload_date;
    private String thumbnail_small;    
    private String thumbnail_medium;
    private String thumbnail_large;
    private String user_name;
    private String user_url;
    private String user_portrait_small;
    private String user_portrait_medium;
    private String user_portrait_large;
    private String user_portrait_huge;
    private String duration;    
    private String width;
    private String height;
    private String mobile_url;
    
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getUpload_date() {
        return upload_date;
    }
    public void setUpload_date(String upload_date) {
        this.upload_date = upload_date;
    }
    public String getThumbnail_small() {
        return thumbnail_small;
    }
    public void setThumbnail_small(String thumbnail_small) {
        this.thumbnail_small = thumbnail_small;
    }
    public String getThumbnail_medium() {
        return thumbnail_medium;
    }
    public void setThumbnail_medium(String thumbnail_medium) {
        this.thumbnail_medium = thumbnail_medium;
    }
    public String getThumbnail_large() {
        return thumbnail_large;
    }
    public void setThumbnail_large(String thumbnail_large) {
        this.thumbnail_large = thumbnail_large;
    }
    public String getUser_name() {
        return user_name;
    }
    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
    public String getUser_url() {
        return user_url;
    }
    public void setUser_url(String user_url) {
        this.user_url = user_url;
    }
    public String getUser_portrait_small() {
        return user_portrait_small;
    }
    public void setUser_portrait_small(String user_portrait_small) {
        this.user_portrait_small = user_portrait_small;
    }
    public String getUser_portrait_medium() {
        return user_portrait_medium;
    }
    public void setUser_portrait_medium(String user_portrait_medium) {
        this.user_portrait_medium = user_portrait_medium;
    }
    public String getUser_portrait_large() {
        return user_portrait_large;
    }
    public void setUser_portrait_large(String user_portrait_large) {
        this.user_portrait_large = user_portrait_large;
    }
    public String getUser_portrait_huge() {
        return user_portrait_huge;
    }
    public void setUser_portrait_huge(String user_portrait_huge) {
        this.user_portrait_huge = user_portrait_huge;
    }
    public String getDuration() {
        return duration;
    }
    public void setDuration(String duration) {
        this.duration = duration;
    }
    public String getWidth() {
        return width;
    }
    public void setWidth(String width) {
        this.width = width;
    }
    public String getHeight() {
        return height;
    }
    public void setHeight(String height) {
        this.height = height;
    }
    public String getTags() {
        return tags;
    }
    public void setTags(String tags) {
        this.tags = tags;
    }
    public String getStats_number_of_likes() {
        return stats_number_of_likes;
    }
    public void setStats_number_of_likes(String stats_number_of_likes) {
        this.stats_number_of_likes = stats_number_of_likes;
    }
    public String getStats_number_of_plays() {
        return stats_number_of_plays;
    }
    public void setStats_number_of_plays(String stats_number_of_plays) {
        this.stats_number_of_plays = stats_number_of_plays;
    }
    public String getStats_number_of_comments() {
        return stats_number_of_comments;
    }
    public void setStats_number_of_comments(String stats_number_of_comments) {
        this.stats_number_of_comments = stats_number_of_comments;
    }
    public VimeoMedia() {
        super();
        // TODO Auto-generated constructor stub
    }
    public VimeoMedia(String id, String title, String description, String url,
            String upload_date, String thumbnail_small,
            String thumbnail_medium, String thumbnail_large, String user_name,
            String user_url, String user_portrait_small,
            String user_portrait_medium, String user_portrait_large,
            String user_portrait_huge, String duration, String width,
            String height, String tags, String stats_number_of_likes,
            String stats_number_of_plays, String stats_number_of_comments) {

        super();
        this.id = id;
        this.title = title;
        this.description = description;
        this.url = url;
        this.upload_date = upload_date;
        this.thumbnail_small = thumbnail_small;
        this.thumbnail_medium = thumbnail_medium;
        this.thumbnail_large = thumbnail_large;
        this.user_name = user_name;
        this.user_url = user_url;
        this.user_portrait_small = user_portrait_small;
        this.user_portrait_medium = user_portrait_medium;
        this.user_portrait_large = user_portrait_large;
        this.user_portrait_huge = user_portrait_huge;
        this.duration = duration;
        this.width = width;
        this.height = height;
        this.tags = tags;
        this.stats_number_of_likes = stats_number_of_likes;
        this.stats_number_of_plays = stats_number_of_plays;
        this.stats_number_of_comments = stats_number_of_comments;
    }
    private String tags;
    private String stats_number_of_likes;
    private String stats_number_of_plays;
    private String stats_number_of_comments;

	public String getVimeoTitleAndUrl() {
		return getTitle()+"@"+getUrl();
	}
	
	/**
	 * @return the mobile_url
	 */
	public String getMobile_url() {
		return mobile_url;
	}
	/**
	 * @param mobileUrl the mobile_url to set
	 */
	public void setMobile_url(String mobileUrl) {
		mobile_url = mobileUrl;
	}   
    
  
}
