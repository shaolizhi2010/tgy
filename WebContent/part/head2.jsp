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
	style="padding: 3px; padding-top: 15px; padding-bottom: 10px; margin: 0px;">

	<div class=" col-sm-9" style="padding-left: 0px; ">
		 

	</div>
	
	 
	
	
	<div class="  col-sm-3  ">

		<%
			if (isNewUserFlag) {
		%>
		<a class="btn btn-success pull-right" onclick="firstTryFunction()"
			style="color: #fff; font-weight: bold;"> <span
			class="glyphicon   glyphicon-arrow-right "></span> 创建 自己的收藏 &nbsp;&nbsp;
		</a>

		<%
			} else {
		%>
		<div class="col-sm-6" style="padding: 3px;">
			<a class=" btn btn-success  col-sm-12"
				style="color: #fff; font-weight: bold; margin-right: 0px;"
				onclick="preAddPageFunction()" href="#"> 添加网址 </a>
			<!-- #449044 -->
		</div>
		<div class="col-sm-6" style="padding: 3px; margin-left: 0px;">
			<a class="btn btn-default col-sm-12" data-toggle="dropdown"
				style="margin-left: 0px;" href="#" role="button"
				aria-expanded="false"> 更多操作 <span class="caret"></span>
			</a>
			<ul class="dropdown-menu" role="menu">
				<li><a onclick="preEditAll()"> 编辑 </a></li>
				<li><a onclick="preCreateFolderFunction()">添加分类</a></li>
			</ul>
		</div>
		<%
			}
		%>

	</div>

</div>

<!-- 分割线
<hr class="divider col-sm-12"
	style="border-top: 2px solid #eee; margin-top: 1px;"> -->
