<%@page import="com.tgy.web.vo.BookmarkData"%>
<%@page import="com.tgy.entity.User"%>
<%@page import="com.tgy.util.C"%>
<%@page import="com.tgy.entity.Folder"%>
<%@page import="com.tgy.vo.BreadCrumb"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="com.tgy.util.U"%>
<%@page import="java.util.List"%>
<%@ page language="java" pageEncoding="UTF-8"%>

<%
	BookmarkData bookmarkData = U.param(request, "bookmarkData", BookmarkData.class);
	User user = null;
	if(bookmarkData!=null && bookmarkData.curUser!=null){
		user = bookmarkData.curUser;
	}
	String userName = "";
	String userID = "";

	String disableIfNotLogin="disabled";
	if (user != null) {
		userName = user.name;
		disableIfNotLogin = "";
	} else {
		userName = "未找到";
	}
%>

<!--------面包屑----------->
<div class=" col-sm-12"
	style="padding: 3px; padding-top: 15px; padding-bottom: 15px; margin: 0px;">

	<div class=" col-md-7">
		<a href="<%=request.getContextPath() %>/<%=userName%>"><span class="glyphicon glyphicon-home"
			style="font-size: 16px; font-weight: bold;"></span></a> <a href="<%=request.getContextPath() %>/<%=userName%>"><span
			style="font-size: 20px; font-weight: bold; margin-left: 3px;"><%=userName%></span></a>
			
			 

		<!-- background-color: #fff; -->
		<span style="font-size: 18px; font-weight: bold; margin-left: 3px;">
			/</span>
		<%
			BreadCrumb bread = null;
			if (U.param(request, "bread", BreadCrumb.class) != null) {
				bread = U.param(request, "bread", BreadCrumb.class);
				while (bread != null) {
		%>
		<a href="<%=bread.link%>"> 
			<span style="font-size: 18px; font-weight: bold; margin-left: 3px;">
					<%=U.shortString(bread.name, 20)%></span> 
		</a>
		<span style="font-size: 18px; font-weight: bold; margin-left: 3px;">/</span>
		<%
			bread = bread.child;
				}
			}
		%>
	</div>
	<div class=" col-sm-1  pull-right"> 
		<a  class=" " ng-click="preCreateFolderFunction()" href="#"><span class="glyphicon glyphicon-plus" style="font-size: 16px;font-weight:bold; "></span></a>
		
			<a href="#" class="dropdown-toggle" data-toggle="dropdown">
				<span class="glyphicon glyphicon-chevron-down" style="font-size: 16px;font-weight:bold; "></span>
			</a>
			<ul class="dropdown-menu" role="menu">
				<li><a ng-click="preCreateFolderFunction()" href="#">创建分类</a></li>
				<li class="divider"></li>
				
				<li class="<%=disableIfNotLogin%>"><a ng-click="preEditAll()"  href="#">编辑</a></li>
				<li class="divider"></li>
				<li><a
					href="http://localhost/tgy/folder/?fid=5448792a7890c8799d303726">水平显示</a></li>
				<li><a
					href="http://localhost/tgy/folder/?fid=5448792a7890c8799d303726&show=h">垂直显示</a></li>
			</ul>
	</div>

</div>

<!-- 分割线 -->
<hr class="divider col-md-12"
	style="border-top: 1px solid #eee; margin-top: 1px;">