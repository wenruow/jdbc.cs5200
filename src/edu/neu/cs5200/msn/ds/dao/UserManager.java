package edu.neu.cs5200.msn.ds.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import edu.neu.cs5200.msn.ds.model.User;


public class UserManager {
	DataSource ds;
	
	public UserManager()
	{
		try {
			Context ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/MovieSocialNetworkDB");
			System.out.println(ds);
		  } catch (NamingException e) {
			e.printStackTrace();
		  }
	}
	public void createUser(User user)
	{
		String sql="insert into user value(?,?,?,?,?,?)";
		try {
			Connection connection=ds.getConnection();
			PreparedStatement statement=connection.prepareStatement(sql);
			statement.setString(1, user.getUsername());
			statement.setString(2, user.getPassword());
			statement.setString(3, user.getFirstName());
			statement.setString(4, user.getLastName());
			statement.setString(5, user.getEmail());
			Date date=user.getDateOfBirth();
			java.sql.Date sDate=new java.sql.Date(date.getTime());
			statement.setDate(6, sDate);
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public User readUser(String username)
	{
		String sql="select * from user where username=?";
		
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement=connection.prepareStatement(sql);
			statement.setString(1, username);
			ResultSet result=statement.executeQuery();
			User one=new User();
			while(result.next())
			{
				one.setUsername(result.getString("username"));
				one.setPassword(result.getString("password"));
				one.setFirstName(result.getString("firstName"));
				one.setLastName(result.getString("lastName"));
				one.setEmail(result.getString("email"));
				one.setDateOfBirth(result.getDate("dateOfBirth"));
				return one;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	public List<User> selectAll(){
		
		String sql="SELECT * FROM user";
		List<User> users=new ArrayList<User>();
		//System.out.println(connection);
		
		try {
			Connection connection =ds.getConnection();
			PreparedStatement statement=connection.prepareStatement(sql);
			ResultSet results=statement.executeQuery();
			while(results.next()){
				String firstName=results.getString("firstName");
				String lastName=results.getString("lastName");
				String email=results.getString("email");
				String username=results.getString("username");
				String password=results.getString("password");
				Date BirthDate=results.getDate("dateOfBirth");
				
				User developer =new User( username,  password,  firstName,
						 lastName,  email,  BirthDate );
				users.add(developer);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return users;
	}
	
	public void updateUser(String username, User user)
	{
		String sql="update user set password=?, firstName=?, lastName=?,email=?,dateOfBirth=? where username=?";
		try {
			Connection connection=ds.getConnection();
			PreparedStatement statement=connection.prepareStatement(sql);
			statement.setString(6, username);
			statement.setString(1, user.getPassword());
			statement.setString(2, user.getFirstName());
			statement.setString(3, user.getLastName());
			statement.setString(4, user.getEmail());
			Date date=user.getDateOfBirth();
			java.sql.Date sDate=new java.sql.Date(date.getTime());
			statement.setDate(5, sDate);
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void deleteUser(String username)
	{
		String sql="Delete  from user where username=?";
		try {
			Connection connection=ds.getConnection();
			PreparedStatement statement=connection.prepareStatement(sql);
			statement.setString(1, username);
			statement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
