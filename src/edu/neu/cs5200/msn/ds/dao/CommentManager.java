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

import edu.neu.cs5200.msn.ds.model.Comment;

public class CommentManager {
DataSource ds;
	
	public CommentManager()
	{
		try {
			Context ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/MovieSocialNetworkDB");
			System.out.println(ds);
		  } catch (NamingException e) {
			e.printStackTrace();
		  }
	}
	public void createComment(Comment comment)
	{
		String sql="insert into comment value(null,?,?,?,?)";
		try {
			Connection connection=ds.getConnection();
			PreparedStatement statement=connection.prepareStatement(sql);
			
			statement.setString(1, comment.getComment());
			statement.setString(3,comment.getUserId());
			statement.setString(4, comment.getMovieId());
			
			Date date=comment.getDate();
			java.sql.Date sDate=new java.sql.Date(date.getTime());
			statement.setDate(2, sDate);
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Comment readCommentForId(int commentId)
	{
		String sql="select * from comment where id=?";
		
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement=connection.prepareStatement(sql);
			statement.setInt(1, commentId);
			ResultSet result=statement.executeQuery();
			Comment one=new Comment();
			while(result.next())
			{
				one.setId(commentId);
				one.setMovieId(result.getString("movieId"));
				one.setUserId(result.getString("userId"));
				one.setDate(result.getDate("date"));
				return one;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	public List<Comment> readAllCommentForUserName(String username)
	{
		String sql="select * from comment where userid=?";
		List<Comment> comments=new ArrayList<Comment>();
		
		try {
			Connection connection=ds.getConnection();
			PreparedStatement statement=connection.prepareStatement(sql);
			statement.setString(1, username);
			ResultSet results=statement.executeQuery();
			while(results.next()){
				int id=results.getInt("id");
				String Comments=results.getString("comment");
				String userId=results.getString("userid");
				
				Date date=results.getDate("date");
				String movieId=results.getString("movieId");
				Comment comment =new Comment( id,  Comments,  date,  userId,
						 movieId );
				comments.add(comment);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return comments;
	}
	public List<Comment> readAllCommentForMovie(String movieId)
	{
		String sql="select * from comment where userid=?";
		List<Comment> comments=new ArrayList<Comment>();
		
		try {
			Connection connection=ds.getConnection();
			PreparedStatement statement=connection.prepareStatement(sql);
			statement.setString(1, movieId);
			ResultSet results=statement.executeQuery();
			while(results.next()){
				int id=results.getInt("id");
				String Comments=results.getString("comment");
				String userId=results.getString("userid");
				
				Date date=results.getDate("date");
				String movieid=results.getString("movieId");
				Comment comment =new Comment( id,  Comments,  date,  userId,
						 movieid );
				comments.add(comment);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return comments;
	}
	public List<Comment> readAllComments(){
		
		String sql="SELECT * FROM comment";
		List<Comment> comments=new ArrayList<Comment>();
		//System.out.println(connection);
		
		try {
			Connection connection =ds.getConnection();
			PreparedStatement statement=connection.prepareStatement(sql);
			ResultSet results=statement.executeQuery();
			while(results.next()){
				int id=results.getInt("id");
				String Comments=results.getString("comment");
				String userId=results.getString("userid");
				
				Date date=results.getDate("date");
				String movieId=results.getString("movieId");
				Comment comment =new Comment( id,  Comments,  date,  userId,
						 movieId );
				comments.add(comment);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return comments;
	}
	
	public void updateComment(int commentId, String newComment)
	{
		String sql="update comment set  comment=?where id=?";
		try {
			Connection connection=ds.getConnection();
			PreparedStatement statement=connection.prepareStatement(sql);
			
			statement.setInt(2, commentId);
			statement.setString(1, newComment);
			
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void deleteComment(int commentId)
	{
		String sql="Delete  from comment where id=?";
		try {
			Connection connection=ds.getConnection();
			PreparedStatement statement=connection.prepareStatement(sql);
			statement.setInt(1, commentId);
			statement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
