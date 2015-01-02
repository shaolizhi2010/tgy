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
<%@include file="../part/common.jsp" %>
<%@include file="../part/show-user-data.jsp" %>
<%
	String groupID = U.paramString(request,"groupID"); 
	String folderID =  U.paramString(request,"folderID"); 
%>
<!DOCTYPE html>
<html >
<head lang="en">
<jsp:include page="../part/head-meta.jsp" />
<jsp:include page="../part/importAtHead.jsp" />
</head>
<body>
	<jsp:include page="../part/head.jsp" />
	<jsp:include page="../part/public-tabs.jsp" />
	<div class="  col-sm-12" style="margin-top: 20px;"></div>
 
	<!-- 主页面开始 -->
	<div class="container col-sm-9 clearfix no-padding">
		<!-------- 书签主页面 --------->
		<div id="pageMain" class="col-sm-10 col-sm-offset-1 container no-padding" >
			<input id="groupID" type="hidden" value="<%=groupID %>" />
			<input id="folderID" type="hidden" value="<%=folderID %>" />
		 	<div class=" container col-sm-12 sub-page-with-title">
	 			<div class="sub-page-title">
					<span>收藏网址</span>
				</div>
			<div class="col-sm-12 container sub-page-body no-padding" style=" ">
						
				<div style="margin-top: 20px;">
					<label for="pageUrl">网址</label>
						<textarea    id="pageUrl"  class="form-control hover-focus" rows="3"></textarea>
				</div>
				<div style="margin-top: 20px;" id="createPage-name-div">
					<label for="pageName">网站名称</label> <input placeholder="不填则自动获取"
						class="form-control  " id="pageName" type="text" />
				</div>
				<div style="margin-top: 20px;">
					<button onclick="createPageFunction()"  
						type="button" class="btn btn-primary">添加网址</button>
				 	<button onclick="javascript:history.go(-1)" type="button" class="btn btn-default"  >取消</button>
				</div>
		 	</div>
	 	</div>
	</div>
	</div>
		<!-- 书签主页面结束-->
		
		<!-- 右边栏开始 -->
		<div class="col-sm-3" style="  ">
			<div class="  col-sm-12" style=" "></div>
			
			<%//如果正在显示某用户
				if(StringUtils.isNotBlank(showUserName)){
					%>
					<jsp:include page="../part/user-info.jsp" />
					<div class="  col-sm-12" style="margin-top: 20px;"></div>
					<%
				}
			%>
			<%//如果已经登陆
				if(loginFlag){
					%>
					<jsp:include page="../part/page-slide-myHotClicks.jsp" />
					<div class="  col-sm-12" style="margin-top: 20px;"></div>
					<%
				}
			%>
			<jsp:include page="../part/folder-slide-follow.jsp" />
		</div>
		<!-- 右边栏结束 -->


	<jsp:include page="../part/foot.jsp" />
	<jsp:include page="../part/importAtFoot.jsp" />
	
	<script src="<%=request.getContextPath()%>/myjs/common.js"></script>
	<script src="<%=request.getContextPath()%>/myjs/group/group.page.create.js"></script>
</body>

</html>