<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="com.tgy.entity.Page"%>
<%@page import="com.tgy.dao.PageDao"%>
<%@page import="org.apache.commons.collections.CollectionUtils"%>
<%@page import="com.tgy.statistic.entity.Link"%>
<%@page import="com.tgy.statistic.service.LinkService"%>
<%@page import="com.tgy.entity.Folder"%>
<%@page import="com.tgy.util.U"%>
<%@page import="java.util.List"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="user-data.jsp" %>
<div id="hot-click-page" class="container col-sm-12 clearfix sub-page-with-title"  >
	<div class="sub-page-title">
		<span>常用网站</span>
	</div>
	<div class="sub-page-body col-sm-12 no-padding">
	<%
			List<Page> pages = new PageDao().getByUserID(loginUserID , null);
			if (!CollectionUtils.isEmpty(pages)) {
				if (pages.size() > 8)
					pages = pages.subList(0, 8);
	%>
	<%
		for (Page p : pages) {
					String link = p.url;
					if (link != null && !link.startsWith("http:")) {
						link = "http://" + link; 
					}
					if(StringUtils.isBlank(p.name)){
						continue;
					}
	%>
	<div>
		<a target="_blank" href="<%=link%>" class="col-sm-10 no-padding"  
		 onclick="openLink('<%=p.id %>','page')" > 
		 	<span class="glyphicon glyphicon-star" style="color: #ffd76e;"></span> <span 
			style="color: #1155cc;"> <%=U.shortTitle(p.name,6 ) %></span> <!-- #ff076e #1155cc; 0000cc-->
		</a> 
		<span class="col-sm-2" style="color: #1155cc;">  <%=p.clicks%></span>
	</div>
	<%
		}//end for
	}// end if empty
	%>
	</div>
</div>