package edu.neu.cs5200.ds;

import java.util.Date;

public class Comment {

	private int id;
	private int UserId;
	private int MovieId;
	private String comment;
	private Date date;
	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Comment(int id, int userId, int movieId, String comment, Date date) {
		super();
		this.id = id;
		UserId = userId;
		MovieId = movieId;
		this.comment = comment;
		this.date = date;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return UserId;
	}
	public void setUserId(int userId) {
		UserId = userId;
	}
	public int getMovieId() {
		return MovieId;
	}
	public void setMovieId(int movieId) {
		MovieId = movieId;
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
	
	
}
