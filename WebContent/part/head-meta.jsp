<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="com.tgy.entity.User"%>
<%@page import="com.tgy.util.C"%>
<%@page import="com.tgy.entity.Folder"%>
<%@page import="com.tgy.util.U"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="show-user-data.jsp" %>
<%@include file="bookmark-data.jsp" %>
<%
	String title = C.title;
	String userName = "";
	if(loginUserName!=null){
		userName = loginUserName;
	}
	if( StringUtils.isNotBlank(showUserName )  ){
		title = showUserName+" - " +title;
	}
	if( StringUtils.isNotBlank(showFolderName )  ){
		title = showFolderName+" - " + title;
	}
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title><%=title %></title>
<meta name='description' content='网址盒子，保存喜欢的和常用的网址，随时随地都能用，网址再也不会丢！网址盒子，最好的网站收藏、网站分享和网站推荐平台，找网站，到网址盒子。'/>
<meta name='keywords' content='网址盒子 网址收藏 美剧 电影 电视剧 人人 美剧下载 美剧资源 美剧网盘 '/>
<meta http-equiv="Content-Language" content="zh-CN" />
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<meta name="baidu-site-verification" content="ZHXLJ1hVsp" />
<meta name="google-site-verification" content="OUnrPw6694Qv4V01iZHP-sENOEoS7jpMHBD5h5B_wRw" />
<meta property="qc:admins" content="5733125577675205216375" />
<meta name="sogou_site_verification" content="Qd7VdxsXzG"/>
<meta name="msvalidate.01" content="272A4B91678EA4354B575FB689707BEE" />
<link rel="sitemap" type="application/xml" title="Sitemap" href="Sitemap.txt" />
<link rel="shortcut icon"   href="images/favicon.ico" />
<link rel="icon"  href="favicon.ico" />