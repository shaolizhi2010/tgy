<%@page import="com.tgy.web.vo.BookmarkData"%>
<%@page import="com.tgy.entity.User"%>
<%@page import="com.tgy.util.C"%>
<%@page import="com.tgy.entity.Folder"%>
<%@page import="com.tgy.vo.BreadCrumb"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="com.tgy.util.U"%>
<%@page import="java.util.List"%>
<%@ page language="java" pageEncoding="UTF-8"%>

<%@include file="common.jsp"%>
<%@include file="show-user-data.jsp"%>
<%@include file="bookmark-data.jsp"%>

<%
	boolean isNewUserFlag = false;
	if (request.getAttribute("isNewUser") != null) {
		isNewUserFlag = true;
	}

	String showUserNameShort = showUserName;
	if (showUserNameShort != null && showUserNameShort.length() > 16) {
		showUserNameShort = showUserNameShort.substring(0, 16);
	}

	String showFolderLink = "#";
	if (showFolder != null) {
		showFolderLink = contextPath + "/folder/" + showFolderID;
	}
%>

<!--------面包屑----------->
<div class=" col-sm-12"
	style="padding: 1px;   background-color: #999;">

</div>

<!-- 分割线
<hr class="divider col-sm-12"
	style="border-top: 2px solid #eee; margin-top: 1px;"> -->
