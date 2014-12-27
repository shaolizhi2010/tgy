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
<% 
boolean loginFlag = false;
User loginUser = null;
String loginUserName = "临时用户";
String loginUserID = "";
String loginUserHeadImgUrl = "";
Object userObj = request.getSession().getAttribute(C.user);
if (userObj!= null && ((User)userObj).id!=null) {
	//login user
	loginFlag = true;
	loginUser = (User) userObj;
	loginUserName = loginUser.name;
	loginUserID = "";
	if(loginUser.id!=null){
		//System.out.println("loginUser.id : "+loginUser.id);
		//System.out.println("loginUser.name : "+loginUser.name);
		loginUserID = loginUser.id.toString();
	}
	loginUserHeadImgUrl = loginUser.headImgUrl;
}
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
if(StringUtils.isNoneBlank(loginUserID) &&
		StringUtils.isNotBlank(showUserID) && 
		StringUtils.equals(loginUserID, showUserID)){
	isSelf = true;
}
%>