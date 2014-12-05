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
<%@include file="user-data.jsp"%>
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

	<div class=" col-sm-2" style="padding-left: 0px; ">
		<!-- 当前用户 和 用户下拉列表  begin-->
		<div class="  col-sm-12" style=" ">
			<a class="  col-sm-12" style="padding-left:0px;  " href="#"
				id="showUserHistoryBtn" type="button" data-toggle="dropdown"
				aria-haspopup="true" role="button" aria-expanded="false">
				<span class="glyphicon glyphicon-home" style="font-size: 16px; font-weight: bold;"></span>
				<span>用户:</span>  <span
					style="  font-weight: bold; font-size: 20px;"><%=showUserNameShort%></span>
				<span class="caret"></span>
			</a>
			
			<ul id="showUserHistoryContent" class="dropdown-menu col-sm-6" role="menu" aria-labelledby="showUserHistoryBtn" style="   ">
 
		  </ul>
			 
		</div>
<!-- 当前用户 和 用户下拉列表 end -->

	</div>
	
	<div id="god-div" class="col-md-7 ui-widget no-padding" style=""> 
		<input id="god-input" class="form-control hover-focus" style="height: 45px;" placeholder="网址、关键字、网站名称" />
		<div id= "god-content-div" class="  col-sm-12"  style="display: none;position: absolute; z-index: 1000;   background-color: #fff;border: 1px solid #333; ">
			 
		</div> 
		<a id="god_link" href="#" target="_blank" style="display: none;"></a>
	</div>
	
	
	<div class="  col-sm-3  ">

		<%
			if (isNewUserFlag) {
		%>
		<a class="btn btn-success pull-right" ng-click="firstTryFunction()"
			style="color: #fff; font-weight: bold;"> <span
			class="glyphicon   glyphicon-arrow-right "></span> 创建 自己的收藏 &nbsp;&nbsp;
		</a>

		<%
			} else {
		%>
		<div class="col-sm-6" style="padding: 3px;">
			<a class=" btn btn-success  col-sm-12"
				style="color: #fff; font-weight: bold; margin-right: 0px;"
				ng-click="preAddPageFunction()" href="#"> 添加网址 </a>
			<!-- #449044 -->
		</div>
		<div class="col-sm-6" style="padding: 3px; margin-left: 0px;">
			<a class="btn btn-default col-sm-12" data-toggle="dropdown"
				style="margin-left: 0px;" href="#" role="button"
				aria-expanded="false"> 更多操作 <span class="caret"></span>
			</a>
			<ul class="dropdown-menu" role="menu">
				<li><a ng-click="preEditAll()"> 编辑 </a></li>
				<li><a ng-click="preCreateFolderFunction()">添加分类</a></li>
				<li><a ng-click="preUploadBookmarkFunction()">导入书签</a></li>
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
