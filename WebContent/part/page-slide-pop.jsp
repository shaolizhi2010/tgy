<%@page import="com.tgy.util.PageUtil"%>
<%@page import="org.apache.commons.collections.CollectionUtils"%>
<%@page import="com.tgy.statistic.entity.Link"%>
<%@page import="com.tgy.statistic.service.LinkService"%>
<%@page import="com.tgy.entity.Folder"%>
<%@page import="com.tgy.util.U"%>
<%@page import="java.util.List"%>
<%@ page language="java" pageEncoding="UTF-8"%>

<%@include file="bookmark-data.jsp" %> 

<div  class="container col-sm-12 clearfix"
	style="padding: 2px; padding-left: 20px; margin-bottom: 20px; border-left: 1px solid #eee;">

 
	<%

		if (showFolder != null) {

			String tagName = showFolder.name;
			LinkService lService = new LinkService();
			List<Link> links = lService.searchByTagName(tagName);
			
			if (!CollectionUtils.isEmpty(links)) {
				if(links.size()>8)links=links.subList(0, 8);
	%>
	<p style="padding: 3px;">大家常用的<%=(tagName) %>网站：</p>
	<%
		for (Link link : links) {
					if (link.keeps > 1 //收藏次数大于1 才显示，只收藏过一次的可能是一些比较个性化的网址，没必要显示
					) {
	%>

	<div style="margin-top: 10px;   height: 20px;">
		<a target="_blank" href="<%=link.url%>" class="col-sm-8"
			> <span
			class="glyphicon glyphicon-star" style="color: #ffd76e;"></span> <span
			style="color: #1155cc;"> <%= U.shortString(link.title,6) %></span> <!-- #ff076e #1155cc; 0000cc-->
		</a> <a onclick="createPageFunction('<%=link.title%>','<%=link.url%>')" href="#" class="col-sm-2"
			style="padding-left: 1px; padding-right: 1px;"> <span
			class="glyphicon glyphicon glyphicon-plus" 
			style="padding-right: 1px;"></span>
		</a>
	</div>
	<%
		}
				}//end for
	%>
	<a class="col-sm-12" target="_blank" href="<%=request.getContextPath()%>/tag/<%=tagName%>"
		style="margin-top: 10px;"> >>查看更多<%=tagName%>网站 </a>
	<%
		}

		}//end if
	%>



</div>