<%@page import="com.tgy.tools.CreateRobotUserTool"%>
<%@page import="com.tgy.entity.User"%>
<%@page import="com.tgy.service.UserService"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="java.util.List"%>
<%@ page language="java" pageEncoding="UTF-8"%>
 
 <%
 int userCount = 1;
 if(request.getParameter("count")!=null){
	 userCount = Integer.parseInt(request.getParameter("count"));
 }
 List<String> usernames = new CreateRobotUserTool().createRobotUser(userCount);
 for(String name: usernames){
	 out.println(name+"<br/>");	 
 }
 out.print("done");
 %>
