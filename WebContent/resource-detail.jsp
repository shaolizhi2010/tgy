<%@page import="java.net.URLEncoder"%>
<%@page import="com.tgy.util.FuliDou"%>
<%@page import="com.tgy.entity.TopicPic"%>
<%@page import="com.tgy.entity.TopicSumary"%>
<%@page import="org.apache.commons.collections.CollectionUtils"%>
<%@page import="com.tgy.entity.Topic"%>
<%@page import="com.tgy.service.TopicService"%>
<%@page import="org.apache.commons.lang3.EnumUtils"%>
<%@page import="com.tgy.entity.Tag"%>
<%@page import="com.tgy.statistic.service.TagService"%>
<%@page import="org.apache.commons.lang3.math.NumberUtils"%>
<%@page import="com.tgy.util.PageType"%>
<%@page import="java.util.Random"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.tgy.util.C"%>
<%@page import="com.tgy.entity.Folder"%>
<%@page import="com.tgy.util.U"%>
<%@page import="com.tgy.entity.Page"%>
<%@page import="java.util.List"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>

<%
String url = request.getParameter("url");
String resourceName = request.getParameter("name");
%>

<%@include file="part/common.jsp"%>
<%
	//String url = request.getAttribute("javax.servlet.forward.request_uri").toString();
	//System.out.println("url : "+ url ) ;
	//String queryString  = request.getQueryString();
%>


<!DOCTYPE html>
<html>
<head lang="en">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title><%=resourceName%></title>
<meta name='description' content='<%=resourceName%>百度网盘'/>
<meta name='keywords' content='<%=resourceName%> 百度网盘'/>
<meta http-equiv="Content-Language" content="zh-CN" />
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<meta name="360_union_verify" content="4c97f9c47ed923e1b134e74d5525697b">
<link rel="sitemap" type="application/xml" title="Sitemap" href="Sitemap.txt" />
<link rel="shortcut icon"   href="http://www.webhezi.com/images/favicon.ico" />
<link rel="icon"  href="http://www.webhezi.com/images/favicon.ico" />
<jsp:include page="part/importAtHead.jsp" />
</head>
<body>


	<jsp:include page="part/head.jsp" />
	<jsp:include page="part/public-tabs.jsp" />

	<!-- 书签主页面开始 -->
	<div class="container col-sm-12 clearfix "
		style="padding-top: 0px; padding-left: 0px;">

		
		
		<iframe  src="<%=url %>"  
				frameborder="0" scrolling="auto" width="80%" height="100%" 
				style=""
				onload="document.all['myframe'].style.width=myframe.document.body.scrollWidth" >
			</iframe>
			
		
	</div>

	<jsp:include page="part/foot.jsp" />

	<script src="<%=request.getContextPath()%>/myjs/common.js"></script>
</body>

</html>