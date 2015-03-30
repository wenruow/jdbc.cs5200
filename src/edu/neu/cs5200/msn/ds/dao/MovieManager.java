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

import edu.neu.cs5200.msn.ds.model.Movie;
public class MovieManager {
	
	DataSource ds;
	
	public MovieManager()
	{
		try {
			Context ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/MovieSocialNetworkDB");
			System.out.println(ds);
		  } catch (NamingException e) {
			e.printStackTrace();
		  }
		
	}
	//create a movie
	public Movie create(Movie movie)
	{
		String sql="insert into movie values(?,?,?,?)";
		
		try {
			
			Connection connection = ds.getConnection();
			
			PreparedStatement statement=connection.prepareStatement(sql);
			statement.setString(1, movie.getId());
			statement.setString(2, movie.getTitle());
			statement.setString(3, movie.getPosterImage());
			Date date=movie.getReleasedDate();
			java.sql.Date sDate=new java.sql.Date(date.getTime());
			statement.setDate(4, sDate);
			
			statement.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public Movie readMovie(String movieId)
	{
		String sql="select * from movie where id=?";
		
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement=connection.prepareStatement(sql);
			statement.setString(1, movieId);
			ResultSet result=statement.executeQuery();
			Movie one=new Movie();
			while(result.next())
			{
				one.setId(result.getString("id"));
				one.setTitle(result.getString("title"));
				one.setPosterImage(result.getString("posterImage"));
				one.setReleasedDate(result.getDate("releasedDate"));
				return one;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	public List<Movie> readAllMovies(){
		
		String sql="SELECT * FROM movie";
		List<Movie> movies=new ArrayList<Movie>();
		//System.out.println(connection);
		
		try {
			Connection connection =ds.getConnection();
			PreparedStatement statement=connection.prepareStatement(sql);
			ResultSet results=statement.executeQuery();
			while(results.next()){
				String id=results.getString("id");
				String title=results.getString("title");
				String posterImage=results.getString("posterImage");
				
				
				Date releasedDate=results.getDate("releasedDate");
				Movie movie =new Movie(  id,  title,  posterImage,  releasedDate );
				movies.add(movie);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return movies;
	}
	
	public void updateUser(String movieId, Movie movie)
	{
		String sql="update movie set  title=?, posterImage=?,releasedDate=? where id=?";
		try {
			Connection connection=ds.getConnection();
			PreparedStatement statement=connection.prepareStatement(sql);
			statement.setString(1,movie.getTitle() );
			statement.setString(2, movie.getPosterImage());
			
			
			Date date=movie.getReleasedDate();
			java.sql.Date sDate=new java.sql.Date(date.getTime());
			statement.setDate(3, sDate);
			statement.setString(4, movieId);
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void deleteUser(String movieId)
	{
		String sql="Delete  from movie where id=?";
		try {
			Connection connection=ds.getConnection();
			PreparedStatement statement=connection.prepareStatement(sql);
			statement.setString(1, movieId);
			statement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//retrieve a movie by id
	//update a movie by id
	//delete a movie by id
	
}
