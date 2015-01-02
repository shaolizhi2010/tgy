<%@page import="com.tgy.entity.User"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.tgy.web.vo.BookmarkData"%>
<%@page import="com.tgy.util.C"%>
<%@page import="com.tgy.entity.Folder"%>
<%@page import="com.tgy.util.U"%>
<%@page import="com.tgy.entity.Page"%>
<%@page import="java.util.List"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="part/common.jsp" %>
<%@include file="part/bookmark-data.jsp" %> 
<%@include file="part/show-user-data.jsp" %>
<!DOCTYPE html>
<html ng-app="pageMainApp" ng-controller="pageMainCtrl">
<head lang="en">
<jsp:include page="part/head-meta.jsp" />
<jsp:include page="part/importAtHead.jsp" />
</head>
<body>
	<jsp:include page="part/head.jsp" />
	<div class=" col-sm-12 no-padding" style="border: 1px solid #999;  "></div>
 
 	<jsp:include page="part/private-tabs.jsp" />
	<div class="  col-sm-12" style="margin-top: 20px;"></div>
	
	<div class=" col-sm-4 no-padding" >
		<div class=" col-sm-10 col-sm-offset-1 no-padding" >
			<jsp:include page="part/history-folder-part.jsp"></jsp:include>
		</div>
		
	</div>
	<div class=" col-sm-8 no-padding" >
		<jsp:include page="part/history-page-part.jsp"></jsp:include>
		
	</div>
	
	<jsp:include page="part/foot.jsp" />
	<jsp:include page="part/foot-prompt.jsp" />
	<jsp:include page="part/importAtFoot.jsp" />
	
	<script src="<%=request.getContextPath()%>/myjs/pageMainApp.js"></script>
	<script src="<%=request.getContextPath()%>/myjs/common.js"></script>
</body>

</html>