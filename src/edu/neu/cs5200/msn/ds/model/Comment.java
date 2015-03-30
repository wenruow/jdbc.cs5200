package edu.neu.cs5200.msn.ds.model;

import java.util.Date;

public class Comment {
	private int id;
	private String comment;
	private Date date;
	private String userId;
	private String movieId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getMovieId() {
		return movieId;
	}
	public void setMovieId(String movieId) {
		this.movieId =movieId;
	}
	public Comment(int id, String comment, Date date, String userId,
			String movieId) {
		super();
		this.id = id;
		this.comment = comment;
		this.date = date;
		this.userId = userId;
		this.movieId = movieId;
	}
	public Comment() {
		super();
	}

}
