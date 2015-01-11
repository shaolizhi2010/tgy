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

<%@include file="../part/common.jsp" %>
<%@include file="../part/login-user-data.jsp" %>
<%
FollowsService fos = new FollowsService();
UserService us = new UserService();

List<Follows> follows = fos.list(new ConditionMap().add("toUserID", loginUserID), null, 0);
List<User> users = new ArrayList<User>();
for(Follows follow : follows){
	String fromUserID = follow.fromUserID;
	User user = us.getByID(fromUserID);
	if(user!=null){
		users.add(user);	
	}
}
PageService ps = new PageService();
%>

<!DOCTYPE html>
<html>
<head lang="en">
<jsp:include page="../part/head-meta.jsp" />
<jsp:include page="../part/importAtHead.jsp" />
</head>
<body>
	<jsp:include page="../part/head.jsp" />
	<jsp:include page="../part/private-tabs.jsp" />
	<div class="  col-sm-12" style=""><h3>我的粉丝</h3></div>
	<!-- 主体内容 -->
	<div class=" col-sm-9 no-padding container">
		<!-- one column start -->
		<div class="col-sm-6" style=" ">
			<%
		 	for (int i=0;i<users.size();i+=2) {
		 		User user = users.get(i);
		 		String name = user.name;
				if (name != null && name.length() > 10) {
					name = name.substring(0, 10) + "..";
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
				if (name != null && name.length() > 10) {
					name = name.substring(0, 10) + "..";
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
	<!-- 菜单 -->
 	<div class=" col-sm-3 no-padding" >
		<div class=" col-sm-10 col-sm-offset-1 no-padding" >
			<jsp:include page="part/social.menu.slide.jsp"/>
		</div>
	</div>
	
	<input type="hidden" id="contextPath" value="<%=contextPath%>">
	<input type="hidden" id="pageID" value="personal-social">
	<input type="hidden" id="pageID-level2" value="personal-social-fans">
 
	<jsp:include page="../part/foot.jsp" />
	<jsp:include page="../part/importAtFoot.jsp" />
	
	<script src="<%=request.getContextPath()%>/myjs/pageMainApp.js"></script>
	<script src="<%=request.getContextPath()%>/myjs/common.js"></script>
</body>
</html>