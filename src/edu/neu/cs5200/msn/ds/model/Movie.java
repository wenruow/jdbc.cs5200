package edu.neu.cs5200.msn.ds.model;

import java.util.Date;



public class Movie {
	private String id;
	private String title;
	private String posterImage;
	private Date releasedDate;
	public Movie() {
		super();
	}
	public Movie(String id, String title, String poster, Date releasedDate) {
		super();
		this.id = id;
		this.title = title;
		this.posterImage = poster;
		this.releasedDate = releasedDate;
	}
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
	public String getPosterImage() {
		return posterImage;
	}
	public void setPosterImage(String poster) {
		this.posterImage = poster;
	}
	public Date getReleasedDate() {
		return releasedDate;
	}
	public void setReleasedDate(Date releasedDate) {
		this.releasedDate = releasedDate;
	}
	

}
