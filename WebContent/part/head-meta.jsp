<%@page import="com.tgy.entity.User"%>
<%@page import="com.tgy.util.C"%>
<%@page import="com.tgy.entity.Folder"%>
<%@page import="com.tgy.util.U"%>
<%@ page language="java" pageEncoding="UTF-8"%>


<%
	String title = C.title;
	Folder curFolder = U.param(request, "curFolder", Folder.class);
	User user = U.param(request, C.user, User.class);
	String userName = "";
	if(user!=null){
		userName = user.name;
	}
	if(curFolder !=null){
		title = userName +"收藏的"+curFolder.name+"网站 - 网址盒子";
	}
%>

<title><%=title %></title>
<meta name='description' content='糖果云，最好的网站收藏、网站分享和网站推荐平台，找网站，到糖果云。'/>
<meta name='keywords' content='糖果云 网络收藏夹  美剧网站 电影网站 好网站 电视剧网站 '/>
<meta http-equiv="Content-Language" content="zh-CN" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<meta http-equiv="X-UA-Compatible" content="IE=Edge" />

<link rel="sitemap" type="application/xml" title="Sitemap" href="Sitemap.txt" />
<link rel="shortcut icon" type="image/vnd.microsoft.icon" href="favicon.ico" />
<link rel="icon"  href="favicon.ico" />
