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
				if (linkLength > 40) {
					linkshow = linkshow.substring(0, 20) + "..."
							+ linkshow.substring(linkLength - 20);
				}

			}
	%>

	<a target="_blank" class="editable pageMark c-a" href="<%=p.url%>"
		dataid="<%=p.id%>" dataname="<%=pageName%>"
		style="padding-top: 10px; display: block; height: 1px;"> <span
		class="glyphicon glyphicon-star" style="color: #ffd76e;"></span> <span
		style="color: #1155cc;"> <%=pageName%></span> - <%=linkshow%> <!-- #ff076e #1155cc; 0000cc-->
	</a> <br>


	<%
		}
	%>

	<a ng-click="preAddPageFunction(' ',' ')" href="#"
		style="margin-top: 30px; display: block; height: 1px;"> <span
		class="glyphicon glyphicon-plus" style="color: #ffd76e;"></span> <span
		style="color: #1155cc;">添加网址</span> - 添加网址
	</a>

</div>
<!-- 显示网址页面结束 -->