package edu.neu.cs5200.msn.ds.model;

public class Cast {

	private String charactorName;
	private String movieId;
	private String actorId;
	private int castId;
	public String getCharactorName() {
		return charactorName;
	}
	public void setCharactorName(String charactorName) {
		this.charactorName = charactorName;
	}
	public String getMovieId() {
		return movieId;
	}
	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}
	public String getActorId() {
		return actorId;
	}
	public void setActorId(String actorId) {
		this.actorId = actorId;
	}
	public int getCastId() {
		return castId;
	}
	public void setCastId(int castId) {
		this.castId = castId;
	}
	public Cast(String charactorName, String movieId, String actorId, int castId) {
		super();
		this.charactorName = charactorName;
		this.movieId = movieId;
		this.actorId = actorId;
		this.castId = castId;
	}
	public Cast() {
		super();
	}
	
}
