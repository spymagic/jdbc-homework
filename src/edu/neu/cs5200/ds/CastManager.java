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


public class CastManager {

	
	DataSource ds;
	public CastManager(){
		try {
			Context jndi = new InitialContext();
			ds = (DataSource)jndi.lookup("java:comp/env/jdbc/JDBC");
			System.out.print(ds);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
          }
	}
	
	
	public void createCast(Cast newCast){
		String sql= "insert into cast (id, actorId, movieId, characterName) values(?,?,?,?)";
		Connection connection = null;
		try {
			connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql); 
			statement.setInt(1, newCast.getId());
			statement.setInt(2, newCast.getActorId());
			statement.setInt(3, newCast.getMovieId());
			statement.setString(4, newCast.getCharacterName());
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


	public List<Cast> readAllCasts(){
		String sql="select * from cast";
		List<Cast> casts = new ArrayList<Cast>(); 
		Connection connection = null;
		try {
			connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql); 
			ResultSet results=statement.executeQuery();
			while(results.next()){	
				Cast cast = new Cast();
				cast.setId(results.getInt("id"));
				cast.setActorId(results.getInt("actorId"));
				cast.setMovieId(results.getInt("movieId"));
				cast.setCharacterName(results.getString("characterName"));
				casts.add(cast);
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
		return casts;
		
		}
	
	public List<Cast> readAllCastsForUsername(String username){
		String sql="select * from cast c, user u join on(u.id=c.userId) where u.username=? ";
	    Connection connection= null;
		List<Cast> casts = new ArrayList<Cast>(); 
	    try {
	    connection=ds.getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, username);
		ResultSet results = statement.executeQuery();
		while(results.next()){	
			Cast cast = new Cast();
			cast.setId(results.getInt("id"));
			cast.setActorId(results.getInt("actorId"));
			cast.setMovieId(results.getInt("movieId"));
			cast.setCharacterName(results.getString("characterName"));
			casts.add(cast);
		
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
	return casts;
	
	}

	public List<Cast> readAllCastsForMovie(String movieId){
		String sql="select * from cast where movieId=? ";
		Connection connection= null;
		List<Cast> casts = new ArrayList<Cast>(); 
		try {
			connection=ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, movieId);
			ResultSet results = statement.executeQuery();
			while(results.next()){	
				Cast cast = new Cast();
				cast.setId(results.getInt("id"));
				cast.setActorId(results.getInt("actorId"));
				cast.setMovieId(results.getInt("movieId"));
				cast.setCharacterName(results.getString("characterName"));
				casts.add(cast);
	
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
		return casts;
	}
	
	
	
	public Cast readCastForId(String castId){
		String sql="select * from cast where id=?";
		Connection connection=null;
		try {
			connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, castId);
			ResultSet results = statement.executeQuery();
			if(results.next()){
				Cast cast=new Cast();
				cast.setId(results.getInt("id"));
				cast.setActorId(results.getInt("actorId"));
				cast.setMovieId(results.getInt("movieId"));
				cast.setCharacterName(results.getString("characterName"));
				return cast;
				
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
	
	
	public void updateCast(int castId, Cast cast){
		String sql="update cast set id=?, userId=?, movieId=?, cast=?, date=?";
		Connection connection = null;
		try {
			connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, cast.getId());
			statement.setInt(2, cast.getActorId());
			statement.setInt(3, cast.getMovieId());
			statement.setString(4, cast.getCharacterName());
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
	
	public void deleteCast(int castId){
		String sql="delete from cast where id=?";
		Connection connection = null;
		try {
			connection=ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, castId);
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