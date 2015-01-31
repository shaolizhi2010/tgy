<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="com.tgy.entity.User"%>
<%@page import="com.tgy.util.C"%>
<%@page import="com.tgy.entity.Folder"%>
<%@page import="com.tgy.util.U"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="show-user-data.jsp" %>
<%@include file="bookmark-data.jsp" %>
<%
	String title = "网址盒子";
	String metaWord = "";
	if(request.getAttribute("keywordForMeta")!=null){
		metaWord = (String)request.getAttribute("keywordForMeta");
	}
	metaWord = metaWord.replace("null", "");
	

	if(metaWord!=null){
		title = metaWord+" - " + title;
		 
	}
	else{
		String userName = "";
		if(loginUserName!=null){
			userName = loginUserName;
		}
		if( StringUtils.isNotBlank(showUserName )  ){
			title = showUserName+"的网址收藏  - " +title;
		}
		if( StringUtils.isNotBlank(showFolderName )  ){
			title = showFolderName+" - " + title;
		}
	}
	
	String desc = "网址盒子，专注于网址收藏和网址分享，存网址-找网址，到网址盒子";
	if(metaWord!=null){
		desc = metaWord+" - " + desc;
	}
	else{
		
	}
	
	String keyword = "网址盒子 网址收藏   网址导航 网址分享 自定义网址导航  在线收藏夹  网络收藏夹 网站收藏 网址导航 在线保存网址 网站收集 云收藏 ";
	if(metaWord!=null){
		keyword = metaWord+"   " + keyword;
	}
	
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title><%=title %></title>
<meta name='description' content='<%=desc%>'/>
<meta name='keywords' content='<%=keyword%>'/>
<meta http-equiv="Content-Language" content="zh-CN" />
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<meta name="baidu_union_verify" content="22b8f1d552d7aa7971e01d375296bf63">
<meta name="google-site-verification" content="OUnrPw6694Qv4V01iZHP-sENOEoS7jpMHBD5h5B_wRw" />
<meta property="qc:admins" content="5733125577675205216375" />
<meta name="sogou_site_verification" content="Qd7VdxsXzG"/>
<meta name="msvalidate.01" content="272A4B91678EA4354B575FB689707BEE" />
<meta name="domain_verify" content="pmrgi33nmfuw4ir2ej3wkytimv5gsltdn5wselbcm52wszbchirgizrqgq2genjzgyyggnbuha2dkolfgfstenbwgvrtsztggnsdsmzcfqrhi2lnmvjwc5tfei5dcnbsge2tkmrtg43dqobypu">
<link rel="sitemap" type="application/xml" title="Sitemap" href="Sitemap.txt" />
<link rel="shortcut icon"   href="images/favicon.ico" />
<link rel="icon"  href="favicon.ico" />