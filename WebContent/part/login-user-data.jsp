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
long fuliScore = 0L;

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
	fuliScore = loginUser.fulidou;
	request.setAttribute("fuliScore", fuliScore);
}
%>
<input type="hidden" id="loginFlag" value="<%=loginFlag%>">
<input type="hidden" id="loginUserID" value="<%=loginUserID%>">
<input type="hidden" id="fuliScore" value="<%=fuliScore%>">