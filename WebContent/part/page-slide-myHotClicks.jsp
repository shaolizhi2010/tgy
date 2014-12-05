
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
<div class="container col-sm-12 clearfix"
	style="padding: 2px; padding-left: 20px; margin-bottom: 20px; ">
	<div>
		<span style="font-size: 14px;">我常用的网站：</span>
	</div>
	<%
		if (loginUser != null) { 

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

	<div style="margin-top: 10px; padding-top:10px;padding-bottom: 10px; ">
		<a target="_blank" href="<%=link%>" class="col-sm-8" style="padding-left:3px; "
		 ng-click="openLink('<%=p.id %>','page')"
		> <span
			class="glyphicon glyphicon-star" style="color: #ffd76e;"></span> <span 
			style="color: #1155cc;"> <%=U.shortTitle(p.name,10  ) %></span> <!-- #ff076e #1155cc; 0000cc-->
		</a> 
		<span class="col-sm-3" style="color: #1155cc;"> / <%=p.clicks%></span>
	</div>
	<%
		}//end for
	%>

	<%
		}// end if empty

		}//end if userID
		else {
	%>
	<div style="font-size: 12px;">
		&nbsp;&nbsp;&nbsp;&nbsp;登录后可用，欢迎 <a ng-click="preLoginFunction()" href="#">登录</a> 或 <a
			ng-click="preAddUserFunction()" href="#">注册</a>
	</div>
	<%
		}
	%>
</div>