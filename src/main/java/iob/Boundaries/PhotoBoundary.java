package iob.Boundaries;

import java.util.Date;
import java.util.HashSet;

public class PhotoBoundary {
	private long id;
	private String url;
	private Date uploadDate;
	private long uploaderId;
	private HashSet<TagsBoundary> tags;
	private long likes;
	private HashSet<UserBoundary> likedBy;
	
	public PhotoBoundary() {}
	
	public PhotoBoundary(long id, String url, Date uploadDate, long uploaderId, HashSet<TagsBoundary> tags, long likes,
			HashSet<UserBoundary> likedBy) {
		super();
		this.id = id;
		this.url = url;
		this.uploadDate = uploadDate;
		this.uploaderId = uploaderId;
		this.tags = tags;
		this.likes = likes;
		this.likedBy = likedBy;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

	public long getUploaderId() {
		return uploaderId;
	}

	public void setUploaderId(long uploaderId) {
		this.uploaderId = uploaderId;
	}

	public HashSet<TagsBoundary> getTags() {
		return tags;
	}

	public void setTags(HashSet<TagsBoundary> tags) {
		this.tags = tags;
	}

	public long getLikes() {
		return likes;
	}

	public void setLikes(long likes) {
		this.likes = likes;
	}

	public HashSet<UserBoundary> getLikedBy() {
		return likedBy;
	}

	public void setLikedBy(HashSet<UserBoundary> likedBy) {
		this.likedBy = likedBy;
	}
	
}
