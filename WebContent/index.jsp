<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="edu.neu.cs5200.msn.ds.dao.*,edu.neu.cs5200.msn.ds.model.*,java.util.*,java.text.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Hello</h1>
	<%/*
		UserManager userDAO=new UserManager();
		//User user= new User();
		//userDAO.deleteUser("jia.m");
		//out.println(user.getFirstName());
		/*
		user.setUsername("wang.wenr");
		user.setPassword("199010");
		user.setFirstName("WANG");
		user.setLastName("Wenruo");
		user.setEmail("362781063@qq.com");*/
		
		
		ActorManager movieDAO= new ActorManager();
		Actor actor=new Actor();
		/*
		actor.setId("567");
		actor.setFirstName("WANG");
		actor.setLastName("Wenruo");
		
		SimpleDateFormat sdf=new SimpleDateFormat("M/dd/yyyy");
		String releaseDate="10/12/1990";
		Date date=sdf.parse(releaseDate);
		actor.setDateOfBirth(date);
		movieDAO.createActor(actor);
		*//*
		List<Actor> actors=movieDAO.readAllActors();
		for(Actor actor : actors)
		{
			out.println(actor.getDateOfBirth());
		}*/
		//actor=movieDAO.readActor("123");
		//out.println(actor.getDateOfBirth());
		movieDAO.deleteActor("123");
	%>
</body>
</html>