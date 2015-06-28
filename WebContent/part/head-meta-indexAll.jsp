<%@page import="com.tgy.util.PageType"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="com.tgy.entity.User"%>
<%@page import="com.tgy.util.C"%>
<%@page import="com.tgy.entity.Folder"%>
<%@page import="com.tgy.util.U"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="show-user-data.jsp" %>
<%
	String title = "网盘盒子-网盘分享(网址盒子)";
	String metaWord = "";

	if(request.getAttribute("keywordForMeta")!=null){
		metaWord = (String)request.getAttribute("keywordForMeta");
	}
	
	String tagName = request.getAttribute("tagName")!=null?(String)request.getAttribute("tagName"):"";
	String type =  request.getAttribute("type")!=null?(String)request.getAttribute("type"):"";
	
	if(StringUtils.equalsIgnoreCase(type, PageType.resource.name())){
		metaWord = "百度云网盘资源";
		if(StringUtils.isNoneBlank(tagName)){
			metaWord = tagName+metaWord  ;
		}
	}

	
	metaWord = metaWord.replace("null", "");
	

	if(metaWord!=null){
		title = metaWord+" - " + title;
		 
	}
	
	String desc = "网盘盒子，百度网盘分享 百度网盘搜索";
	if(metaWord!=null){
		desc = metaWord+" - " + desc;
	}
	else{
		
	}
	
	String keyword = "网盘盒子 百度网盘 百度盘 百度云 百度网盘分享 百度网盘搜索 网址盒子";
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
<meta name='TTUNION_verify' content='38e8c8ce307dc868f14f9a3fafff72fd'>
<meta name="baidu_union_verify" content="1e072fca0862723b8454eabb2cdca0bf">
<link rel="sitemap" type="application/xml" title="Sitemap" href="Sitemap.txt" />
<link rel="shortcut icon"   href="images/favicon.ico" />
<link rel="icon"  href="favicon.ico" />