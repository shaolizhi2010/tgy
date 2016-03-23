<%@page import="com.tgy.entity.Follows"%>
<%@page import="com.tgy.util.ConditionMap"%>
<%@page import="com.tgy.service.FollowsService"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="com.tgy.service.PageService"%>
<%@page import="com.tgy.service.UserService"%>
<%@page import="com.tgy.entity.User"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.tgy.web.vo.BookmarkData"%>
<%@page import="com.tgy.util.C"%>
<%@page import="com.tgy.entity.Folder"%>
<%@page import="com.tgy.util.U"%>
<%@page import="com.tgy.entity.Page"%>
<%@page import="java.util.List"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>

<%@include file="../part/common.jsp"%>
<%@include file="../part/login-user-data.jsp"%>
<%
	 
	//if(request.getAttribute("fuliScore")!=null){
	//	fuliScore = String.valueOf(request.getAttribute("fuliScore"));
	//}
%>

<!DOCTYPE html>
<html>
<head lang="en">
<jsp:include page="../part/head-meta.jsp" />
<jsp:include page="../part/importAtHead.jsp" />
</head>
<body>
	<jsp:include page="../part/head.jsp" />



	<!-- 主体内容 -->
	<div class="col-sm-offset-1 col-sm-6 no-padding container">


		<!--one info card -->
		<div class="col-sm-6   info-card">
			<div class="col-sm-12">
				<div class="col-sm-3">
					<a href="#"> <img class="img-responsive"
						style="width: 60px; height: 60px; border-radius: 60px;" alt="已登录"
						src="<%=request.getContextPath()%>/images/ava/ava1.png">
					</a>
				</div>
				<div class="col-sm-8 user-name">
					<a href="#" title="123"> <span> </span><span
						style="color: #999; font-size: 26px; font-weight: normal;">
							张三丰</span></a>
					<div class="share-info">
						<span style="color: orange"> 0 福利豆</span>
					</div>
				</div>
			</div>

		</div>
		<!--one info card end-->


<div class="col-sm-8  ">我关注的标签...</div>
<div class="col-sm-8  ">我关注的资源...</div>
<div class="col-sm-8  ">我想要的资源...</div>
<div class="col-sm-8  ">我发布的资源...</div>

	</div>
	<!-- 主体内容 end-->


	<input type="hidden" id="contextPath" value="<%=contextPath%>">


	<jsp:include page="../part/foot.jsp" />
	<jsp:include page="../part/importAtFoot.jsp" />

	<script src="<%=request.getContextPath()%>/myjs/pageMainApp.js"></script>
	<script src="<%=request.getContextPath()%>/myjs/common.js"></script>
</body>
</html>