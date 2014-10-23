<%@page import="com.tgy.util.U"%>
<%@page import="com.tgy.entity.Bookmark"%>
<%@page import="java.util.List"%>
<%@ page language="java" pageEncoding="UTF-8"%>

<%
	List<Bookmark> bookmars = U.paramList(request, "sysBookmarks");
	for(Bookmark bookmark : bookmars){
		%>
		
		  <a pre-href="localhost/bookmark/ " href=""
           class="  col-md-12 c-a" style=" ">
           <%=bookmark.name %>
             </a>
		
		<%
	}
%>


 