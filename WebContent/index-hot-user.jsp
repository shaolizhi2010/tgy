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

<%@include file="part/common.jsp" %>
<%@include file="part/bookmark-data.jsp" %> 
<%@include file="part/user-data.jsp" %>
<%
PageService ps = new PageService();
UserService fs = new UserService();
List<User> users = fs.list("-showTimes",10);
%>

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
	<div class=" col-sm-9 no-padding container">
		<!-- one column start -->
		<div class="col-sm-6" style=" ">
			<%
		 	for (int i=0;i<users.size();i+=2) {
		 		User user = users.get(i);
		 		String name = user.name;
				if (name != null && name.length() > 12) {
					name = name.substring(0, 12) + "..";
				}
		 	%> 
		 	
		 	<!--one info card -->
				<div class="col-sm-10   info-card">
					<div class="col-sm-12">
						<div class="col-sm-4">
							<a href="<%=request.getContextPath()%>/u/<%=user.id %>" >
							<%
								if(StringUtils.isBlank(user.headImgUrl)){
									%>
									<span class="glyphicon glyphicon-user" style="font-size: 40px;color: #eee;"></span>
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
						<a href="<%=request.getContextPath()%>/u/<%=user.id %>" >
							<span><%=name %></span></a>
						<div class="share-info"><span></span> <span><%=user.showTimes %> 访问   0关注 </span> </div>
					</div>
					</div>
					
					<!-- links -->
					<div class="col-sm-12 info-card-pages ">
						<%
						List<Page> subPages = ps.list(user.id.toString(),"-clicks", 4);
						for(Page p :subPages){
					 		if(StringUtils.isBlank(p.name)){
					 			continue;
					 		}
						%>
								<a class="col-sm-6 no-padding" href="<%=p.url%>" target="_blank">
									<span >
									<%=U.shortString(p.name, 10)%>
									</span>
								</a>	
							<%
							}
						%>
					</div>
					
				</div>
				<!--one info card end-->
				<%
					}//end for
				%>
		</div>
		<!-- one column end -->
		
		<!-- one column start -->
		<div class="col-sm-6" style=" ">
			<%
		 	 
		  
		 	for (int i=1;i<users.size();i+=2) {
		 		User user = users.get(i);
		 		String name = user.name;
		 		if(StringUtils.isBlank(name)){
		 			continue;
		 		}
				if (name != null && name.length() > 12) {
					name = name.substring(0, 12) + "..";
				}
		 	%> 
		 	
		 	<!--one info card -->
				<div class="col-sm-10   info-card">
					<div class="col-sm-12">
						<div class="col-sm-4">
							<a href="<%=request.getContextPath()%>/u/<%=user.id %>"  >
							<%
								if(StringUtils.isBlank(user.headImgUrl)){
									%>
									<span class="glyphicon glyphicon-user" style="font-size: 40px;color: #eee;"></span>
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
						<a href="<%=request.getContextPath()%>/u/<%=user.id %>" >
							<span><%=name %></span></a>
						<div class="share-info"><span></span> <span><%=user.showTimes %> 访问   0关注 </span> </div>
					</div>
					</div>
					
					<!-- links -->
					<div class="col-sm-12 info-card-pages ">
						<%
							List<Page> subPages = ps.list(user.id.toString(),"-clicks", 4);
							for(Page p :subPages){
						 		if(StringUtils.isBlank(p.name)){
						 			continue;
						 		}
							%>
								<a class="col-sm-6 no-padding" href="<%=p.url%>" target="_blank">
									<span >
									<%=U.shortString(p.name, 10)%>
									</span>
								</a>	
							<%
							}
						%>
					</div>
					
				</div>
				<!--one info card end-->
				<%
					}//end for
				%>
		</div>
		<!-- one column end -->
	
	</div>
	<!-- 主体内容 end-->
	
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
	
	<script src="<%=request.getContextPath()%>/myjs/pageMainApp.js"></script>
	<script src="<%=request.getContextPath()%>/myjs/common.js"></script>
</body>
</html>