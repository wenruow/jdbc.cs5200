package edu.neu.cs5200.msn.ds.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import edu.neu.cs5200.msn.ds.model.Cast;

public class CastManager {
	DataSource ds;
	
	public CastManager()
	{
		try {
			Context ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/MovieSocialNetworkDB");
			System.out.println(ds);
		  } catch (NamingException e) {
			e.printStackTrace();
		  }
	}
	public void createCast(Cast cast)
	{
		String sql="insert into cast value(null,?,?,?)";
		try {
			Connection connection=ds.getConnection();
			PreparedStatement statement=connection.prepareStatement(sql);
			
			statement.setString(1, cast.getCharactorName());
			statement.setString(3,cast.getActorId());
			statement.setString(2, cast.getMovieId());
			
			
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Cast readCastForId(int castId)
	{
		String sql="select * from cast where castId=?";
		
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement=connection.prepareStatement(sql);
			statement.setInt(1, castId);
			ResultSet result=statement.executeQuery();
			Cast one=new Cast();
			while(result.next())
			{
				one.setCastId(castId);
				one.setMovieId(result.getString("movieId"));
				one.setActorId(result.getString("actorId"));
				one.setCharactorName(result.getString("CharactorName"));
				return one;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	public List<Cast> readAllCastForActor(String actorId)
	{
		String sql="select * from CAST where actorId=?";
		List<Cast> comments=new ArrayList<Cast>();
		
		try {
			Connection connection=ds.getConnection();
			PreparedStatement statement=connection.prepareStatement(sql);
			statement.setString(1, actorId);
			ResultSet results=statement.executeQuery();
			while(results.next()){
				int id=results.getInt("CastId");
				
				String name=results.getString("CharactorName");
				
				
				String movieId=results.getString("movieId");
				Cast cast =new Cast( name,  movieId,  actorId,  id);
				comments.add(cast);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return comments;
	}
	public List<Cast> readAllCastForMovie(String movieId)
	{
		String sql="select * from cast where movieId=?";
		List<Cast> casts=new ArrayList<Cast>();
		
		try {
			Connection connection=ds.getConnection();
			PreparedStatement statement=connection.prepareStatement(sql);
			statement.setString(1, movieId);
			ResultSet results=statement.executeQuery();
			while(results.next()){
				int id=results.getInt("id");
				String name=results.getString("charactorName");
				String actorId=results.getString("actorId");
				
				
				Cast cast =new Cast( name, movieId,  actorId,
						 id );
				casts.add(cast);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return casts;
	}
	public List<Cast> readAllCast(){
		
		String sql="SELECT * FROM cast";
		List<Cast> casts=new ArrayList<Cast>();
		//System.out.println(connection);
		
		try {
			Connection connection =ds.getConnection();
			PreparedStatement statement=connection.prepareStatement(sql);
			ResultSet results=statement.executeQuery();
			while(results.next()){
				int id=results.getInt("id");
				String name=results.getString("charactorname");
				String actorId=results.getString("actorId");
				String movieId=results.getString("movieId");
				Cast cast =new Cast( name,movieId,actorId,id );
				casts.add(cast);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return casts;
	}
	
	public void updateCast(int castId, Cast newCast)
	{
		String sql="update cast set  charactorName=?,movieId=? ,actorId=?, where castId=?";
		try {
			Connection connection=ds.getConnection();
			PreparedStatement statement=connection.prepareStatement(sql);
			
			statement.setInt(4, castId);
			statement.setString(1, newCast.getCharactorName());
			statement.setString(2, newCast.getMovieId());
			statement.setString(3, newCast.getActorId());
			
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void deleteCast(int castId)
	{
		String sql="Delete  from cast where id=?";
		try {
			Connection connection=ds.getConnection();
			PreparedStatement statement=connection.prepareStatement(sql);
			statement.setInt(1, castId);
			statement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
