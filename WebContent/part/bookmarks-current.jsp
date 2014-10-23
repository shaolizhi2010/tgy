<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="com.tgy.util.U"%>
<%@page import="com.tgy.entity.Bookmark"%>
<%@page import="java.util.List"%>
<%@ page language="java" pageEncoding="UTF-8"%>

<%
	Bookmark bk = U.param(request, "curBookmark", Bookmark.class);
	if (bk != null && StringUtils.isNotBlank(bk.name)) {
%>

<a pre-href="" href="<%=request.getContextPath() %>/bookmark/?bid=<%=bk.id %>" class="  col-md-12  "
	style=""> <%=bk.name%>
</a>

<%
	}
%>


