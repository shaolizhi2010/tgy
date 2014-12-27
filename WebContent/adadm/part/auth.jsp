<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@ page language="java" pageEncoding="UTF-8"%>

<%

//not pass auth
if(request.getSession(true).getAttribute("auth") == null
	|| !request.getSession(true).getAttribute("auth").equals("true")){
	
	return;
	
} 
 


%>


