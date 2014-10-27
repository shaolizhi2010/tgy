<%@page import="com.tgy.util.C"%>
<%@page import="com.tgy.entity.Folder"%>
<%@page import="com.tgy.util.U"%>
<%@page import="com.tgy.entity.Page"%>
<%@page import="java.util.List"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html ng-app="pageMainApp" ng-controller="pageMainCtrl">
<head lang="en">
<jsp:include page="part/head-meta.jsp" />

<jsp:include page="part/importcss.jsp" />

</head>

<%
	String userID = U.getUserID(request);
	String contextPath = request.getContextPath();

	//request.getSession().setAttribute("tests", "testss");
	//System.out.println("jsp session "+ request.getSession(false).getId());
	
	Folder curFolder = U.param(request, "curFolder", Folder.class);
	String curFolderName = "";
	if(curFolder!=null) curFolderName = curFolder.name;
	String fid = U.paramString(request.getSession(), "fid");

	List<Folder> folders = U.paramList(request, "folders");
	List<Page> pages = U.paramList(request, "pages");
	// Ext ext = new Ext();  // (Ext)request.getAttribute("ext");
%>

<body>
	<jsp:include page="part/head.jsp" />

	<!-- 书签主页面开始 -->
	<div class="container col-md-12 clearfix">
		<!-- 左侧书签列表 -->
		<div id="bookmarks-sidebar" class="container col-md-2"
			style="padding: 2px; border-right: 1px solid #eee;">

			<jsp:include page="part/folder-slide-mine.jsp" />
			<jsp:include page="part/folder-slide-pop.jsp" />
			<jsp:include page="part/folder-slide-follow.jsp" />

		</div>
		<!-- 左侧书签列表结束-->


		<!-------- 右侧 书签主页面 --------->
		<div id="pageMain" class="col-md-7 "
			style="border-left: 1px solid #eee; border-right: 1px solid #eee; padding-left: 40px;">


			<!--------面包屑----------->
			<jsp:include page="part/title-part.jsp" />

			<!------------------ 分割线 ---------------------->
			<hr class="divider col-sm-10"
				style="border-top: 2px solid #eee; margin-top: 1px;">

			<jsp:include page="part/folders-part.jsp" />
			
			<!------------------ 分割线 ---------------------->
			<hr class="divider col-sm-10"
				style="border-top: 1px solid #eee; margin-top: 10px; ">
				
			<jsp:include page="part/pages-part.jsp" />

		</div>
		<!-------- 右侧 书签列表页面 end  --->


		<!-- 显示推荐页面开始 -->
		<div class="col-sm-3" style="padding-top: 20px; padding-bottom: 20px;">

			<jsp:include page="part/page-slide-pop.jsp" />
		</div>
		<!-- 显示推荐页面结束 -->


	</div>
	<!-- 书签主页面结束-->

	<!-- toolbar -->
	<jsp:include page="part/foot-toolbar.jsp" />

	<!-- hidden var begin -->

	<input type="hidden" id="userID" value="<%=userID%>">
	<input type="hidden" id="contextPath" value="<%=contextPath%>">
	<input type="hidden" id="fid" value="<%=fid%>">
	<input type="hidden" id="curFolder" value="<%=curFolderName%>">
	<input type="hidden" id="edit_pid" value="">
	<input type="hidden" id="edit_name" value="">
	<input type="hidden" id="edit_url" value="">

	<!-- hidden var end -->

	<!-- 弹出框开始 -->
	<jsp:include page="window/window.jsp" />
	<!-- 弹出框结束 -->

	<script src="<%=request.getContextPath()%>/myjs/pageMainApp.js"></script>

</body>
<jsp:include page="part/importjs.jsp" />
</html>