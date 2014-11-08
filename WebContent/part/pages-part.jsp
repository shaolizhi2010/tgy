<%@page import="com.tgy.entity.Folder"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="com.tgy.util.U"%>
<%@page import="java.util.List"%>
<%@page import="com.tgy.entity.Page"%>
<%@ page language="java" pageEncoding="UTF-8"%>

<!-- 显示网址页面开始 -->
<div class="col-sm-12" style="padding: 0px; padding-bottom: 10px;  ">

	<%
	
		List<Page> pages = U.paramList(request, "pages");
		for (Page p : pages) {
			String pageName = p.name;
			//name
			if (pageName != null && pageName.length() > 20) {
				pageName = pageName.substring(0, 20) + "...";
			}
			String link =p.url;
			if(link!=null && !link.startsWith("http:")){
				link = "http://"+link;
			}
			String linkshow = p.url;
			//link
			if (linkshow != null) {
				if (linkshow.startsWith("http://")) {
					linkshow = linkshow.replaceAll("http://", "");
				}
				if (linkshow.startsWith("https://")) {
					linkshow = linkshow.replaceAll("https://", "");
				}
				int linkLength = linkshow.length();
				if (linkLength > 32) {
					linkshow = linkshow.substring(0, 16) + "..."
							+ linkshow.substring(linkLength - 16);
				}
			}
	%>

	<a target="_blank" class="col-md-12 editable pageMark " href="<%=link%>" 
		dataid="<%=p.id%>" dataname="<%=p.name%>"   ng-click="openLink('<%=p.id %>','page')"
		style="padding-left: 10px;padding-top: 6px;padding-bottom: 6px;   "> <span
		class="glyphicon glyphicon-star" style="color: orange;"></span> <span
		style="color: #2e8cd8; padding-left: 10px;font-weight: bold;;font-size: 14px;"> <%=pageName%></span> - <span style="color:green;font-size: 11px;"><%=linkshow%></span> <!-- #ff076e #1155cc; 0000cc-->
	</a> <br><!-- 1155cc --> 


	<%
		}
	%>

</div>
<!-- 显示网址页面结束 -->