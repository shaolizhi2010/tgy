<%@page import="com.tgy.entity.group.InterestGroupFolder"%>
<%@page import="com.tgy.util.AuthManager"%>
<%@page import="com.tgy.entity.group.InterestGroupPage"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="com.tgy.entity.group.InterestGroup"%>
<%@ page trimDirectiveWhitespaces="true" %> 
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="com.tgy.entity.User"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.tgy.web.vo.BookmarkData"%>
<%@page import="com.tgy.util.C"%>
<%@page import="com.tgy.entity.Folder"%>
<%@page import="com.tgy.util.U"%>
<%@page import="com.tgy.entity.Page"%>
<%@page import="java.util.List"%>

<%@include file="../login-user-data.jsp"  %> 
<% 
String groupName = "";
String groupID = "";
String showFolderName = "";
String showFolderID = "";

//group data
InterestGroup group = null;
if( request.getAttribute("group") !=null){
	group = U.param(request, "group", InterestGroup.class);
}
if(group!=null){
	groupName = group.name;
	if(group.id!=null){
		groupID = group.id.toString();	
	}
}

//folder data
InterestGroupFolder showFolder = null;
if( request.getAttribute("showFolder") !=null){
	showFolder = U.param(request, "showFolder", InterestGroupFolder.class);
}
if(showFolder!=null){
	showFolderName = showFolder.name;
	if(showFolder.id!=null){
		showFolderID = showFolder.id.toString();
	}
}

List<InterestGroupPage> pages= new ArrayList<InterestGroupPage>();
if(showFolder==null ){
	pages = U.paramList(request, "pages");
}
else{
	pages = showFolder.pages;
}

 
AuthManager authManager = new AuthManager(); 
boolean createAble = authManager.groupCreateAuth(loginUser, group);
boolean editAble =  authManager.groupUpdateAuth(loginUser, group);
boolean deleteAble = false;
%>
<input id="groupName" type="hidden" value="<%=groupName %>" />
<input id="groupID" type="hidden" value="<%=groupID %>" />
<input id="showFolderID" type="hidden" value="<%=showFolderID %>" />