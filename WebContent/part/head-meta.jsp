<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="com.tgy.entity.User"%>
<%@page import="com.tgy.util.C"%>
<%@page import="com.tgy.entity.Folder"%>
<%@page import="com.tgy.util.U"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="show-user-data.jsp" %>
<%
	String title = "网盘盒子";
	String metaWord = "";
	if(request.getAttribute("keywordForMeta")!=null){
		metaWord = (String)request.getAttribute("keywordForMeta");
	}
	metaWord = metaWord.replace("null", "");
	

	if(StringUtils.isNotBlank( metaWord) ){
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
		//if( StringUtils.isNotBlank(showFolderName )  ){
		//	title = showFolderName+" - " + title;
		//}
	}
	
	String desc = "网盘盒子，百度网盘分享";
	if(metaWord!=null){
		desc = metaWord+" - " + desc;
	}
	else{
		
	}
	
	String keyword = "网盘盒子 百度网盘 百度网盘搜索 百度网盘分享  ";
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
<meta name="baidu_union_verify" content="7af084e1b046a9ce7552f5908ff103cb">
<meta name="google-site-verification" content="OUnrPw6694Qv4V01iZHP-sENOEoS7jpMHBD5h5B_wRw" />
<meta property="qc:admins" content="5733125577675205216375" />
<meta name="sogou_site_verification" content="Qd7VdxsXzG"/>
<meta name="msvalidate.01" content="272A4B91678EA4354B575FB689707BEE" />
<meta name="ads8_union_verify" content="d2b3c05f70a983ccd73a75bf413d21ea">
<meta name="domain_verify" content="pmrgi33nmfuw4ir2ej3wkytimv5gsltdn5wselbcm52wszbchirgizrqgq2genjzgyyggnbuha2dkolfgfstenbwgvrtsztggnsdsmzcfqrhi2lnmvjwc5tfei5dcnbsge2tkmrtg43dqobypu">
<link rel="sitemap" type="application/xml" title="Sitemap" href="Sitemap.txt" />
<link rel="shortcut icon"   href="images/favicon.ico" />
<link rel="icon"  href="favicon.ico" />