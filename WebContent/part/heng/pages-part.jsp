<%@page import="java.util.ArrayList"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="com.tgy.util.U"%>
<%@page import="java.util.List"%>
<%@page import="com.tgy.entity.Page"%>
<%@ page language="java" pageEncoding="UTF-8"%>


<%
List<Page> pages = new ArrayList();
	if(pageContext.getAttribute("pages")!=null){
		 pages = (List)pageContext.getAttribute("pages");
	}
	for (Page p : pages) {
		String pageName = p.name;
		//name
		if(pageName!=null && pageName.length()>8){
			pageName = pageName.substring(0,8)+"...";
		}
		 
		 
%>



<a   target="_blank" class=" col-sm-2  " href="<%=p.url%>"
	style="margin-top: 4px; display: block; height: 1px;">  <span
	style="color: #1155cc;"> <%= pageName %></span>  
</a>
 

<%
	}
%>

