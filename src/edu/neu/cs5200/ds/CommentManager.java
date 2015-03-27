package edu.neu.cs5200.ds;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class CommentManager {

	
	DataSource ds;
	public CommentManager(){
		try {
			Context jndi = new InitialContext();
			ds = (DataSource)jndi.lookup("java:comp/env/jdbc/JDBC");
			System.out.print(ds);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
          }
	}
	
	
	public void createComment(Comment newComment){
		String sql= "insert into comment (id, userId, movieId, comment, date) values(?,?,?,?,?)";
		Connection connection = null;
		try {
			connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql); 
			statement.setInt(1, newComment.getId());
			statement.setInt(2, newComment.getUserId());
			statement.setInt(3, newComment.getMovieId());
			statement.setString(4, newComment.getComment());
			statement.setDate(5, (Date) newComment.getDate() );
			statement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}


	public List<Comment> readAllComments(){
		String sql="select * from comment";
		List<Comment> comments = new ArrayList<Comment>(); 
		Connection connection = null;
		try {
			connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql); 
			ResultSet results=statement.executeQuery();
			while(results.next()){	
				Comment comment = new Comment();
				comment.setId(results.getInt("id"));
				comment.setUserId(results.getInt("userId"));
				comment.setMovieId(results.getInt("movieId"));
				comment.setComment(results.getString("comment"));
				comment.setDate(results.getDate("date"));
				comments.add(comment);
			}
									
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return comments;
		
		}
	
	public List<Comment> readAllCommentsForUsername(String username){
		String sql="select * from comment c, user u join on(u.id=c.userId) where u.username=? ";
	    Connection connection= null;
		List<Comment> comments = new ArrayList<Comment>(); 
	    try {
	    connection=ds.getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, username);
		ResultSet results = statement.executeQuery();
		while(results.next()){	
			Comment comment = new Comment();
			comment.setId(results.getInt("id"));
			comment.setUserId(results.getInt("userId"));
			comment.setMovieId(results.getInt("movieId"));
			comment.setComment(results.getString("comment"));
			comment.setDate(results.getDate("date"));
			comments.add(comment);
		
		}
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally{
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	return comments;
	
	}

	public List<Comment> readAllCommentsForMovie(String movieId){
		String sql="select * from comment where movieId=? ";
		Connection connection= null;
		List<Comment> comments = new ArrayList<Comment>(); 
		try {
			connection=ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, movieId);
			ResultSet results = statement.executeQuery();
			while(results.next()){	
				Comment comment = new Comment();
				comment.setId(results.getInt("id"));
				comment.setUserId(results.getInt("userId"));
				comment.setMovieId(results.getInt("movieId"));
				comment.setComment(results.getString("comment"));
				comment.setDate(results.getDate("date"));
				comments.add(comment);
	
			}
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return comments;
	}
	
	
	
	public Comment readCommentForId(String commentId){
		String sql="select * from comment where id=?";
		Connection connection=null;
		try {
			connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, commentId);
			ResultSet results = statement.executeQuery();
			if(results.next()){
				Comment comment=new Comment();
				comment.setId(results.getInt("id"));
				comment.setUserId(results.getInt("userId"));
				comment.setMovieId(results.getInt("movieId"));
				comment.setComment(results.getString("comment"));
				comment.setDate(results.getDate("date"));
				return comment;
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}return null;
		
	}
	
	
	public void updateComment(int commentId, Comment comment){
		String sql="update comment set id=?, userId=?, movieId=?, comment=?, date=?";
		Connection connection = null;
		try {
			connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, comment.getId());
			statement.setInt(2, comment.getUserId());
			statement.setInt(3, comment.getMovieId());
			statement.setString(4, comment.getComment());
			statement.setDate(5, (Date)comment.getDate() );
			statement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void deleteComment(int commentId){
		String sql="delete from comment where id=?";
		Connection connection = null;
		try {
			connection=ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, commentId);
			statement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
		
}