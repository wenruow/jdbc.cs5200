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

import edu.neu.cs5200.msn.ds.model.Actor;

public class ActorManager {
DataSource ds;
	
	public ActorManager()
	{
		try {
			Context ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/MovieSocialNetworkDB");
			System.out.println(ds);
		  } catch (NamingException e) {
			e.printStackTrace();
		  }
	}
	public void createActor(Actor actor)
	{
		String sql="insert into actor value(?,?,?,?)";
		try {
			Connection connection=ds.getConnection();
			PreparedStatement statement=connection.prepareStatement(sql);
			
			statement.setString(1, actor.getId());
			statement.setString(2,actor.getFirstName());
			statement.setString(3, actor.getLastName());
			
			Date date=actor.getDateOfBirth();
			java.sql.Date sDate=new java.sql.Date(date.getTime());
			statement.setDate(4, sDate);
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Actor readActor(String actorId)
	{
		String sql="select * from actor where id=?";
		
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement=connection.prepareStatement(sql);
			statement.setString(1, actorId);
			ResultSet result=statement.executeQuery();
			Actor one=new Actor();
			while(result.next())
			{
				one.setId(actorId);
				one.setFirstName(result.getString("firstName"));
				one.setLastName(result.getString("lastName"));
				one.setDateOfBirth(result.getDate("dateOfBirth"));
				return one;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	public List<Actor> readAllActors(){
		
		String sql="SELECT * FROM Actor";
		List<Actor> actors=new ArrayList<Actor>();
		//System.out.println(connection);
		
		try {
			Connection connection =ds.getConnection();
			PreparedStatement statement=connection.prepareStatement(sql);
			ResultSet results=statement.executeQuery();
			while(results.next()){
				String firstName=results.getString("firstName");
				String lastName=results.getString("lastName");
				String id=results.getString("id");
				
				Date BirthDate=results.getDate("dateOfBirth");
				
				Actor actor =new Actor( id , firstName,
						 lastName,    BirthDate );
				actors.add(actor);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return actors;
	}
	
	public void updateActor(String actionId, Actor actor)
	{
		String sql="update actor set  firstName=?, lastName=?,dateOfBirth=? where id=?";
		try {
			Connection connection=ds.getConnection();
			PreparedStatement statement=connection.prepareStatement(sql);
			
			statement.setString(4, actionId);
			statement.setString(1, actor.getFirstName());
			statement.setString(2, actor.getLastName());
			
			Date date=actor.getDateOfBirth();
			java.sql.Date sDate=new java.sql.Date(date.getTime());
			statement.setDate(3, sDate);
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void deleteActor(String actorId)
	{
		String sql="Delete  from actor where id=?";
		try {
			Connection connection=ds.getConnection();
			PreparedStatement statement=connection.prepareStatement(sql);
			statement.setString(1, actorId);
			statement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
