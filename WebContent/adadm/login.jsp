<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@ page language="java" pageEncoding="UTF-8"%>

<%

String username = request.getParameter("u");
String password = request.getParameter("p");

if( !StringUtils.isBlank(username) && 
	!StringUtils.isBlank(password) &&
	username.equals("shaolizhi2010") &&
	password.equals("cake4you")){
	
	//set session
	request.getSession(true).setAttribute("auth", "true");
	
}
else{
	return;	
}


%>
<jsp:include page="part/menu.jsp" />

