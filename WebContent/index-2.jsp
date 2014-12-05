<%@page import="java.util.ArrayList"%>
<%@page import="com.tgy.web.vo.BookmarkData"%>
<%@page import="com.tgy.util.C"%>
<%@page import="com.tgy.entity.Folder"%>
<%@page import="com.tgy.util.U"%>
<%@page import="com.tgy.entity.Page"%>
<%@page import="java.util.List"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="part/user-data.jsp"  %>  
<%@include file="part/bookmark-data.jsp" %>
<html ng-app="pageMainApp" ng-controller="pageMainCtrl">
<head lang="en">
<jsp:include page="part/head-meta.jsp" />
<jsp:include page="part/importAtHead.jsp" />
</head>
<% 
	String contextPath = request.getContextPath();
%>
<body>
	<jsp:include page="part/head.jsp" />
	<jsp:include page="part/head2.jsp" />
 <jsp:include page="part/folders-fav.jsp" />
	<!-- 书签主页面开始 -->
	<div class="container col-sm-12 clearfix" style="padding-top: 0px;margin-top: 0px; ">  
		<!-- 左侧书签列表 -->
		<!-- 左侧书签列表结束-->
		<!-------- 右侧 书签主页面 --------->
		<div id="pageMain" class="col-sm-9 "
			style="background-color:#fafafa;border-top: 1px solid #eee; border-left: 1px solid #eee; border-right: 1px solid #eee;padding:0px; padding-top:10px; padding-left: 20px;padding-bottom: 40px;">
			<div class="  col-sm-12" style="margin-top: 10px;"></div>
			<jsp:include page="part/pages-all-part.jsp" />
		</div>
		<!-------- 右侧 书签列表页面 end  --->
		<!-- 显示推荐页面开始 -->
		<div class="col-sm-3" style="background-color:#fdfdfd;  padding-bottom: 20px;">
			<jsp:include page="part/page-slide-myHotClicks.jsp" />
			 <jsp:include page="part/folder-slide-follow.jsp" /> 
		</div>
		<!-- 显示推荐页面结束 -->
	</div>
	<!-- 书签主页面结束-->
	<!-- toolbar -->
	<jsp:include page="part/foot-toolbar.jsp" />
	<!-- hidden var begin -->
	<input type="hidden" id="userID" value="<%=showUserID%>">
	<input type="hidden" id="showUserName" value="<%=showUserName%>">
	<input type="hidden" id="loginUserID" value="<%=loginUserID%>">
	<input type="hidden" id="contextPath" value="<%=contextPath%>">
	<input type="hidden" id="fid" value="<%=showFolderID%>">
	<input type="hidden" id="curFolder" value="<%=showFolderName%>">
	<input type="hidden" id="edit_pid" value="">
	<input type="hidden" id="edit_name" value="">
	<input type="hidden" id="edit_url" value="">
	<!-- hidden var end -->

	<!-- 弹出框开始 -->
	<jsp:include page="window/window.jsp" />
	<!-- 弹出框结束 -->
	
	<jsp:include page="part/importAtFoot.jsp" />
	<script src="<%=request.getContextPath()%>/myjs/common.js"></script>
	<script src="<%=request.getContextPath()%>/myjs/pageMainApp.js"></script>
	
	<a id="go-top" href="#"></a>
		<jsp:include page="part/foot.jsp" />
</body>


</html>