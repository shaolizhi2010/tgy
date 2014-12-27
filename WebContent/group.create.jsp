<%@page import="com.tgy.statistic.entity.Link"%>
<%@page import="com.tgy.service.UserService"%>
<%@page import="com.tgy.entity.Discuss"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %> 
<%@page import="com.tgy.entity.User"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.tgy.web.vo.BookmarkData"%>
<%@page import="com.tgy.util.C"%>
<%@page import="com.tgy.entity.Folder"%>
<%@page import="com.tgy.util.U"%>
<%@page import="com.tgy.entity.Page"%>
<%@page import="java.util.List"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@include file="part/common.jsp" %>
<%@include file="part/user-data.jsp" %>
<%
	UserService uService = new UserService();
 
%>
<!DOCTYPE html>
<html >
<head lang="en">
<jsp:include page="part/head-meta.jsp" />
<jsp:include page="part/importAtHead.jsp" />
</head>
<body>
	<jsp:include page="part/head.jsp" />
	<div class="  col-sm-12" style="margin-top: 20px;"></div>
 	<!-- 菜单 -->
 	<div class=" col-sm-3 no-padding" >
		<div class=" col-sm-10 col-sm-offset-1 no-padding" >
			<jsp:include page="part/index.public.menu.jsp"/>
		</div>
	</div>
	<!-- 主页面开始 -->
	<div class="container col-sm-9 clearfix no-padding">  
		<!-------- 书签主页面 --------->
		<div id="pageMain" class="col-sm-9 container no-padding" >
		 	<div class=" container col-sm-12 sub-page-with-title">
	 			<div class="sub-page-title">
					<span>兴趣组名称</span>
				</div>
				<div class="col-sm-12 container sub-page-body no-padding" style=" ">
					<div class="col-sm-12    " style="padding: 20px; ">
				 		<div class="col-sm-8   " style="" >
		            		<input   id="group-create-name"  class="form-control"   placeholder="兴趣组名称"/>
		            	</div>
		            	<div class="col-sm-4   " style="">
			                <button onclick="createGroupFunction()" id="" type="button"
			                        class="btn btn-primary ">确定
			                </button>
		            	</div>
				 	</div>
			 	</div>
		 	</div>
		</div>
		<!--------  主页面 end  --->
		<!-- 显示推荐页面开始 -->
		<div class="col-sm-3" style="  ">
			<div class="  col-sm-12" style=" "></div>
			
			<%//如果正在显示某用户
				if(StringUtils.isNotBlank(showUserName)){
					%>
					<jsp:include page="part/user-info.jsp" />
					<div class="  col-sm-12" style="margin-top: 20px;"></div>
					<%
				}
			%>
			<%//如果已经登陆
				if(loginFlag){
					%>
					<jsp:include page="part/page-slide-myHotClicks.jsp" />
					<div class="  col-sm-12" style="margin-top: 20px;"></div>
					<%
				}
			%>
			<jsp:include page="part/folder-slide-follow.jsp" />
		</div>
		<!-- 显示推荐页面结束 -->


	</div>
	<!-- 书签主页面结束-->

	<!-- toolbar -->
	<jsp:include page="part/foot-toolbar.jsp" />

	<!-- hidden var begin -->

	<input type="hidden" id="loginFlag" value="<%=loginFlag %>">
	<input type="hidden" id="userID" value="<%=showUserID %>">
	<input type="hidden" id="contextPath" value="<%=contextPath%>">
 	<input type="hidden" id="edit_pid" value="">
	<input type="hidden" id="edit_name" value="">
	<input type="hidden" id="edit_url" value="">

	<!-- hidden var end -->

	<!-- 弹出框开始 -->
	<jsp:include page="window/window.jsp" />
	<!-- 弹出框结束 -->
	
	<jsp:include page="part/foot.jsp" />
	<jsp:include page="part/importAtFoot.jsp" />
	
	<script src="<%=request.getContextPath()%>/myjs/common.js"></script>
	<script src="<%=request.getContextPath()%>/myjs/pageMainApp.js"></script>
	<script src="<%=request.getContextPath()%>/myjs/group.create.js"></script>
</body>

</html>