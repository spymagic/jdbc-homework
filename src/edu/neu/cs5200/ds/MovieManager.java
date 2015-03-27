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


public class MovieManager {

	
	DataSource ds;
	public MovieManager(){
		try {
			Context jndi = new InitialContext();
			ds = (DataSource)jndi.lookup("java:comp/env/jdbc/JDBC");
			System.out.print(ds);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
          }
	}
	
	
	public void createMovie(Movie newMovie){
		String sql= "insert into movie (id, title, posterImage, releaseDate) values(?,?,?,?)";
		Connection connection = null;
		try {
			connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql); 
			statement.setInt(1, newMovie.getId());
			statement.setString(2, newMovie.getTitle() );
			statement.setString(3, newMovie.getPosterImage() );
			statement.setDate(4, (Date) newMovie.getReleaseDate() );
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


	public List<Movie> readAllMovies(){
		String sql="select * from movie";
		List<Movie> movies = new ArrayList<Movie>(); 
		Connection connection = null;
		try {
			connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql); 
			ResultSet results=statement.executeQuery();
			while(results.next()){	
				Movie movie = new Movie();
				movie.setId(results.getInt("id"));
				movie.setTitle(results.getString("title"));
				movie.setPosterImage(results.getString("posterImage"));
				movie.setReleaseDate(results.getDate("releaseDate"));
				movies.add(movie);
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
		return movies;
}
	public Movie readMovie(String movieId){
		String sql="select * from movie where id=?";
		Connection connection=null;
		try {
			connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, movieId);
			ResultSet results = statement.executeQuery();
			if(results.next()){
				Movie movie=new Movie();
				movie.setId(results.getInt("id"));
				movie.setTitle(results.getString("title"));
				movie.setPosterImage(results.getString("posterImage"));
				movie.setReleaseDate(results.getDate("releaseDate"));
				return movie;
				
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
	
	
	public void updateMovie(int movieId, Movie movie){
		String sql="update movie set id=?, title=?, posterImage=?, releaseDate=?";
		Connection connection = null;
		try {
			 connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, movie.getId());
			statement.setString(2, movie.getTitle());
			statement.setString(3, movie.getPosterImage());
			statement.setDate(4, (Date) movie.getReleaseDate());
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
	
	public void deleteMovie(int movieId){
		String sql="delete from movie where id=?";
		Connection connection = null;
		try {
			connection=ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, movieId);
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