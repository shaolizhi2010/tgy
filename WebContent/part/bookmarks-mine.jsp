<%@page import="com.tgy.util.U"%>
<%@page import="com.tgy.entity.Bookmark"%>
<%@page import="java.util.List"%>
<%@ page language="java" pageEncoding="UTF-8"%>

<%
	List<Bookmark> bookmarks = U.paramList(request, "userBookmarks");
	for(Bookmark bookmark : bookmarks){
		%>
		
		  <a pre-href=" " href="<%=request.getContextPath() %>/bookmark/?bid=<%=bookmark.id %>"
           class="  col-md-12  " style=" ">
           <%=bookmark.name %>
             </a>
		
		<%
	}
%>


 