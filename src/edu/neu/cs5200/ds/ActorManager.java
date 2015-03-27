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


public class ActorManager {

	
	DataSource ds;
	public ActorManager(){
		try {
			Context jndi = new InitialContext();
			ds = (DataSource)jndi.lookup("java:comp/env/jdbc/JDBC");
			System.out.print(ds);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
          }
	}
	
	
	public void createActor(Actor newActor){
		String sql= "insert into actor (id, firstName, lastName, dateOfBirth) values(?,?,?,?)";
		Connection connection = null;
		try {
			connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql); 
			statement.setInt(1, newActor.getId());
			statement.setString(2, newActor.getFirstName());
			statement.setString(3, newActor.getLastName());
			statement.setDate(4, (Date) newActor.getDateOfBirth() );
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


	public List<Actor> readAllActors(){
		String sql="select * from actor";
		List<Actor> actors = new ArrayList<Actor>(); 
		Connection connection = null;
		try {
			connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql); 
			ResultSet results=statement.executeQuery();
			while(results.next()){	
				Actor actor = new Actor();
				actor.setId(results.getInt("id"));
				actor.setFirstName(results.getString("firstName"));
				actor.setLastName(results.getString("lastName"));
				actor.setDateOfBirth(results.getDate("dateOfBirth"));
				actors.add(actor);
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
		return actors;
}
	public Actor readActor(String actorId){
		String sql="select * from actor where actorId=?";
		Connection connection=null;
		try {
			connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, actorId);
			ResultSet results = statement.executeQuery();
			if(results.next()){
				Actor actor=new Actor();
				actor.setId(results.getInt("id"));
				actor.setFirstName(results.getString("firstName"));
				actor.setLastName(results.getString("lastName"));
				actor.setDateOfBirth(results.getDate("dateOfBirth"));
				return actor;
				
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
	
	
	public void updateActor(int actorId, Actor actor){
		String sql="update actor set id=?, firstName=?, lastName=?, dateOfBirth=?";
		Connection connection = null;
		try {
			 connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, actor.getId());
			statement.setString(2, actor.getFirstName());
			statement.setString(3, actor.getLastName());
			statement.setDate(4, (Date) actor.getDateOfBirth() );
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
	
	public void deleteActor(int actorId){
		String sql="delete from actor where id=?";
		Connection connection = null;
		try {
			connection=ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, actorId);
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