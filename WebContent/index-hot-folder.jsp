<%@page import="com.tgy.service.FolderService"%>
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
<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="part/common.jsp" %>
<%@include file="part/bookmark-data.jsp" %> 
<%@include file="part/show-user-data.jsp" %>
<!DOCTYPE html>
<html>
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
	
	<!-- 主体内容 -->
	<div class=" col-sm-9 no-padding">
	
	<%
 	FolderService fs = new FolderService();
	UserService us = new UserService();
 	List<Folder> folders = fs.list(40);
 	 	for (Folder folder : folders) {
 	 		String name = folder.name;
 			if (name != null && name.length() > 12) {
 				name = name.substring(0, 12) + "..";
 			}
 			String userID = folder.userID.toString();
 			User user = us.getByID(userID);
 			String username = "";
 			if(user!=null){
 				username = user.name ;
 			}
 	 		%> 
		<div class="col-sm-5   info-card">
			<div class="col-sm-4">
				<a href="<%=request.getContextPath()%>/folder/<%=folder.id %>" target="_blank">
				<%
					if(StringUtils.isBlank(user.headImgUrl)){
						%>
						<span class="glyphicon glyphicon-user" style="font-size: 40px;color: #f5f5f5;"></span>
						<%
					}
					else{
						%>
						<img style="height: 40px;width: 40px;" alt="已登录" src="<%=user.headImgUrl%>">
						<%
					}
				%> 
				</a>
			</div>
			<div class="col-sm-8 user-name">
				<a href="<%=request.getContextPath()%>/folder/<%=folder.id %>" target="_blank">
					<span><%=name %></span></a>
				<span> - </span>	
				<a href="<%=request.getContextPath()%>/u/<%=user.id %>" target="_blank">
					<span style="font-size: 12px;color: #999"><%=username %></span></a>
				<div class="share-info"><span></span> <span><%=folder.clicks %> 访问   0关注 </span> </div>
			</div>
		</div>
		
		<%
			}
		%>
		
	</div>
	
	<input type="hidden" id="loginFlag" value="<%=loginFlag %>">
	<input type="hidden" id="userID" value="<%=showUserID %>">
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
	<jsp:include page="part/foot.jsp" />
	<jsp:include page="part/importAtFoot.jsp" />
	<script src="<%=request.getContextPath()%>/myjs/common.js"></script>
	<script src="<%=request.getContextPath()%>/myjs/pageMainApp.js"></script>
</body>

</html>