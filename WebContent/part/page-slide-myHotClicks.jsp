<%@page import="com.tgy.entity.Page"%>
<%@page import="com.tgy.dao.PageDao"%>
<%@page import="org.apache.commons.collections.CollectionUtils"%>
<%@page import="com.tgy.statistic.entity.Link"%>
<%@page import="com.tgy.statistic.service.LinkService"%>
<%@page import="com.tgy.entity.Folder"%>
<%@page import="com.tgy.util.U"%>
<%@page import="java.util.List"%>
<%@ page language="java" pageEncoding="UTF-8"%>

<div class="container col-md-12 clearfix"
	style="padding: 2px; padding-left: 20px; margin-bottom: 20px; border-left: 1px solid #eee;">

	<div>
		<span style="font-size: 14px;">我常用的网站：</span>
	</div>
	<%
		String userID = U.getUserID(request);

		if (userID != null) {

			 List<Page> pages = new PageDao().getByUserID(userID, null);
			
			if (!CollectionUtils.isEmpty(pages)) {
				if(pages.size()>8)pages=pages.subList(0, 8);
	%>
	<%
		for (Page p : pages) {
			String link =p.url;
			if(link!=null && !link.startsWith("http:")){
				link = "http://"+link;
			}
					 
	%>

	<div style="margin-top: 10px;   height: 20px;">
		<a target="_blank" href="<%=link%>" class="col-md-8"
			> <span
			class="glyphicon glyphicon-star" style="color: #ffd76e;"></span> <span
			style="color: #1155cc;"> <%= U.shortTitle(p.name) %></span> <!-- #ff076e #1155cc; 0000cc-->
		</a> 
		<span class="col-md-2" style="color: #1155cc;"> / <%=p.clicks %></span>
	</div>
	<%
				}//end for
	%>
 
	<%
		}// end if empty

		}//end if userID
	%>



</div>