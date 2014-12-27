<%@page import="com.tgy.entity.Folder"%>
<%@page import="com.tgy.vo.BreadCrumb"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="com.tgy.util.U"%>
<%@page import="java.util.List"%>
<%@ page language="java" pageEncoding="UTF-8"%>

<%
	Folder curFolder = U.param(request, "curFolder", Folder.class);
	String pid = "";
	if (curFolder != null) {
		pid = curFolder.pid;
	}
%>

<!--------面包屑----------->
<div class=" col-sm-12" style="padding: 0px; margin: 0px;">
	<div class="col-sm-12" style="padding: 0px; margin: 0px;">
		<ol class="breadcrumb col-sm-9"
			style="padding: 3px;   margin: 0px; background-color: #eee;">
			<!-- background-color: #fff; -->


			/
			<%
				BreadCrumb bread = null;
				if (U.param(request, "bread", BreadCrumb.class) != null) {
					bread = U.param(request, "bread", BreadCrumb.class);
					while (bread != null) {
			%>
			<li><a href="<%=bread.link%>"><%=U.shortTitle(bread.name, 10) %></a></li>
			<%
				bread = bread.child;
					}
				}
			%>

		</ol>
		<ol class="breadcrumb col-sm-3"
			style="padding: 3px; padding-left: 0px; margin: 0px; background-color: #eee;">
			<a onclick="preAddPageFunction( )" href="#" style="padding-left: 1px;padding-right: 1px; "> <span
				class="glyphicon glyphicon glyphicon-plus" style="padding-right:1px;">网址</span>
			</a>
 			<a onclick="preCreateFolderFunction( )" href="#" style="padding-left: 1px;padding-right: 1px; "> <span
				class="glyphicon glyphicon glyphicon-plus"  style="padding-right:1px;">分类</span>
			</a>
		</ol>

	</div>



</div>