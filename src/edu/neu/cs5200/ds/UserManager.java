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


public class UserManager {

	
	DataSource ds;
	public UserManager(){
		try {
			Context jndi = new InitialContext();
			ds = (DataSource)jndi.lookup("java:comp/env/jdbc/JDBC");
			System.out.print(ds);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
          }
	}
	
	
	public void createUser(User newUser){
		String sql= "insert into user (id, username, password, firstName, lastName, email, dateOfBirth) values(?,?,?,?,?,?,?)";
		Connection connection = null;
		try {
			connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql); 
			statement.setInt(1, newUser.getId());
			statement.setString(2, newUser.getUsername());
			statement.setString(3, newUser.getPassword());
			statement.setString(4, newUser.getFirstName());
			statement.setString(5, newUser.getLastName());
			statement.setString(6, newUser.getEmail());
			statement.setDate(7, (Date) newUser.getDateOfBirth() );
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


	public List<User> readAllUsers(){
		String sql="select * from user";
		List<User> users = new ArrayList<User>(); 
		Connection connection = null;
		try {
			connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql); 
			ResultSet results=statement.executeQuery();
			while(results.next()){	
				User user = new User();
				user.setId(results.getInt("id"));
				user.setUsername(results.getString("username"));
				user.setPassword(results.getString("password"));
				user.setFirstName(results.getString("firstName"));
				user.setLastName(results.getString("lastName"));
				user.setEmail(results.getString("email"));
				user.setDateOfBirth(results.getDate("dateOfBirth"));
				users.add(user);
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
		return users;
}
	public User readUser(String userId){
		String sql="select * from user where id=?";
		Connection connection=null;
		try {
			connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, userId);
			ResultSet results = statement.executeQuery();
			if(results.next()){
				User user=new User();
				user.setId(results.getInt("id"));
				user.setUsername(results.getString("username"));
				user.setPassword(results.getString("password"));
				user.setFirstName(results.getString("firstName"));
				user.setLastName(results.getString("lastName"));
				user.setEmail(results.getString("email"));
				user.setDateOfBirth(results.getDate("dateOfBirth"));
				return user;
				
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
	
	
	public void updateUser(int userId, User user){
		String sql="update user set id=?, username=?, password=?, firstName=?, lastName=?, email=?, dateOfBirth=?";
		Connection connection = null;
		try {
			 connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, user.getId());
			statement.setString(2, user.getUsername());
			statement.setString(3, user.getPassword());
			statement.setString(4, user.getFirstName());
			statement.setString(5, user.getLastName());
			statement.setString(6, user.getEmail());
			statement.setDate(7, (Date) user.getDateOfBirth() );
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
	
	public void deleteUser(int userId){
		String sql="delete from user where id=?";
		Connection connection = null;
		try {
			connection=ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, userId);
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