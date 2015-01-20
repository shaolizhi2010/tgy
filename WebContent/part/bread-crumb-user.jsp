<%@page import="com.tgy.dao.FolderDao"%>
<%@page import="com.tgy.util.URLUtils"%>
<%@page import="org.apache.commons.collections.CollectionUtils"%>
<%@page import="com.tgy.web.vo.BookmarkData"%>
<%@page import="com.tgy.entity.Folder"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="com.tgy.util.U"%>
<%@page import="java.util.List"%>
<%@page import="com.tgy.entity.Page"%>
<%@ page language="java" pageEncoding="UTF-8"%>

<%@include file="show-user-data.jsp"  %>  
 
<!-- 面包屑  -->
<div class="col-sm-12" style="padding: 15px;font-size: 20px;border-bottom: 2px solid #eee;">
	<a style=" font-size: 20px; font-weight: bold;" href="<%=request.getContextPath() %>/index-hot-user.jsp"  >用户</a> > 
 	<a style=" font-size: 16px; " href="<%=request.getContextPath() %>/u/<%=showUserID %>"  >
 		<%=showUserName %>
 	</a>
</div>
 