<%@page import="com.tgy.util.U"%>
<%@page import="java.util.List"%>
<%@page import="com.tgy.entity.Page"%>
<%@ page language="java" pageEncoding="UTF-8"%>



<%
	List<Page> pages = U.paramList(request, "pages");
	for (Page p : pages) {
		String pageName = p.name;
		if(pageName!=null && pageName.length()>20){
			pageName = pageName.substring(0,20)+"...";
		}
		String linkshow = p.link;
		if(linkshow!=null && linkshow.length()>20){
			linkshow = linkshow.substring(0,20)+"...";
		}
%>



<a pre-href="<%=p.link %>" target="_blank" class="  c-a" href="<%=p.link %>"
	style="margin-top: 10px; display: block; height: 1px;"> <span
	class="glyphicon glyphicon-star" style="color: #ffd76e;"></span> <span
	style="color: #1155cc;"> <%= pageName %></span> - <%=linkshow %> <!-- #ff076e #1155cc; 0000cc-->
</a>
<br>
 

<%
	}
%>

