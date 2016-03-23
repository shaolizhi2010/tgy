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
<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="login-user-data.jsp"  %>  
<% 
User showUser = null;
String showUserName = "";
String showUserID="";
String showUserHeadImgUrl = "";

if(request.getAttribute("showUser")!=null){
	showUser =  (User)request.getAttribute("showUser");
	showUserName = showUser.name;
	showUserID = "";
	if(showUser.id!=null){
		showUserID =  showUser.id.toString();
	}
	showUserHeadImgUrl = showUser.headImgUrl;
	
}
 
boolean isSelf = false;//用户已登陆并且用户是在查看自己的收藏夹
if(request.getAttribute("isSelf")!=null && (Boolean)request.getAttribute("isSelf")==true ){
	isSelf = true;
}
else if(StringUtils.isNoneBlank(loginUserID) &&
		StringUtils.isNotBlank(showUserID) && 
		StringUtils.equals(loginUserID, showUserID)){
	isSelf = true;
}
%>
<input type="hidden" id="showUserID" value="<%=showUserID%>">
<input type="hidden" id="isSelf" value="<%=isSelf%>">