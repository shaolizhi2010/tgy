<%@page import="java.util.Random"%>
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
<%@include file="part/show-user-data.jsp" %>
<%
PageService ps = new PageService();
UserService us = new UserService();
long userCount = us.count();

int start =0;
if(request.getParameter("start")!=null){
	start = Integer.parseInt(request.getParameter("start"));
}

List<User> users = us.list("-favScore",start,10);
%>

<!DOCTYPE html>
<html>
<head lang="en">
<jsp:include page="part/head-meta.jsp" />
<jsp:include page="part/importAtHead.jsp" />
</head>
<body>
	<jsp:include page="part/head.jsp" />
	<jsp:include page="part/public-tabs.jsp" />
	<div class="  col-sm-12" style="margin-top: 4px;"></div>
	
	<!-- 网站介绍 -->
	<div class="col-sm-9  container" style="background-color:#e8edf1;padding: 30px;padding-top: 10px;padding-bottom: 10px;"> 
		<div class="col-sm-12" style="font-size: 26px;font-weight: bold;color: #de9590;">网盘盒子 - 网址收藏 网站分享</div>
		
		<div class=" col-sm-12" style="margin-top: 10px;"></div>
		
		<div class="col-sm-12" style="font-size: 13px;padding: 10px;color: #222;font-family: Helvetica;">
			<span style="color: #99d233;" class="glyphicon glyphicon-ok-sign"> </span> 
			<span style="font-size: 14px;">在线网址收藏</span> 
			<span style="font-size: 13px;color: #333;"> -- 在线收藏喜欢的网站，不再弄丢网址，不再错过好网站</span>
		</div>
		<div class="col-sm-12" style="font-size: 13px;padding: 10px;color: #222;font-family: Helvetica;">
			<span style="color: #99d233;" class="glyphicon glyphicon-ok-sign"> </span> 
			<span style="font-size: 14px;">上网导航</span> 
			<span style="font-size: 13px;color: #333;"> -- 把自己喜欢的网址，制作成自己的上网导航，设置为首页</span>
		</div>
		<div class="col-sm-12" style="font-size: 13px;padding: 10px;color: #222;font-family: Helvetica;">
			<span style="color: #99d233;" class="glyphicon glyphicon-ok-sign"> </span> 
			<span style="font-size: 14px;">兴趣组</span> 
			<span style="font-size: 13px;color: #333;"> -- 加入兴趣组，和共同爱好的朋友交流，分享网址，淘网站</span>
		</div>
		<div class="col-sm-12" style="font-size: 13px;padding: 10px;color: #222;font-family: Helvetica;">
			<span style="color: #99d233;" class="glyphicon glyphicon-ok-sign"> </span> 
			<span style="font-size: 14px;">分享网址</span> 
			<span style="font-size: 13px;color: #333;"> -- 发送收藏夹的链接给好友，关注好友，查看Ta 收藏了哪些网站</span>
		</div>  
	</div>
	
	
	<!-- 主体内容 -->
	<div class=" col-sm-9 no-padding container">
		<div class="  col-sm-12" style="margin-top: 10px;">
			<h3>活跃用户</h3>
		</div>
		
		<!-- one column start -->
		<div class="col-sm-6  " style=" ">
			<%
		 	for (int i=0;i<users.size();i+=2) {
		 		User user = users.get(i);
		 		String name = user.name;
				if (name != null && name.length() > 8) {
					name = name.substring(0, 8) + "..";
				}
		 	%> 
		 	
		 	<!--one info card -->
				<div class="col-sm-12   info-card">
					<div class="col-sm-12">
						<div class="col-sm-3">
							<a href="<%=request.getContextPath()%>/u/<%=user.id %>" >
							<%
								if(StringUtils.isBlank(user.headImgUrl)){
									%>
									<img class="img-responsive"  style="width: 50px;height: 50px; border-radius:50px;" alt="已登录" src="<%=request.getContextPath()%>/images/ava/ava<%=new Random().nextInt(120)+1%>.png">
									<%
								}
								else{
									%>
									<img class="img-responsive" style="width: 50px;height: 50px; border-radius:50px;" alt="已登录" data-original="<%=user.headImgUrl%>">
									<%
								}
							%> 
							</a>
						</div>
						<div class="col-sm-8 user-name">
						<a href="<%=request.getContextPath()%>/u/<%=user.id %>" title="<%=user.name%>">
							<span><%=name %></span><span style="color:#999; font-size: 9px;font-weight: normal;"> 的收藏夹</span></a>
						<div class="share-info"><span></span> <span><%=user.favScore %>欢迎度     <%=user.pageCount%>网址</span> </div>
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
								<a class="col-sm-6 col-xs-12 no-padding" href="<%=p.url%>" target="_blank">
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
		<div class="col-sm-6  " style=" ">
			<%
		 	 
		  
		 	for (int i=1;i<users.size();i+=2) {
		 		User user = users.get(i);
		 		String name = user.name;
		 		if(StringUtils.isBlank(name)){
		 			continue;
		 		}
				if (name != null && name.length() > 8) {
					name = name.substring(0, 8) + "..";
				}
		 	%> 
		 	
		 	<!--one info card -->
				<div class="col-sm-12   info-card">
					<div class="col-sm-12">
						<div class="col-sm-3">
							<a href="<%=request.getContextPath()%>/u/<%=user.id %>"  >
							<%
								if(StringUtils.isBlank(user.headImgUrl)){
									%>
									<img class="img-responsive" style="width: 50px;height: 50px; border-radius:50px;" alt="已登录" src="<%=request.getContextPath()%>/images/ava/ava<%=new Random().nextInt(120)+1%>.png">
									<%
								}
								else{
									%>
									<img class="img-responsive" style="width: 50px;height: 50px; border-radius:50px;" alt="已登录" data-original="<%=user.headImgUrl%>">
									<%
								}
							%> 
							</a>
						</div>
						<div class="col-sm-8 user-name">
						<a href="<%=request.getContextPath()%>/u/<%=user.id %>" title="<%=user.name%>">
							<span><%=name %></span>
							<span style="color:#999; font-size: 9px;font-weight: normal;"> 的收藏夹</span></a>
						<div class="share-info"><span></span> <span><%=user.favScore %>欢迎度     <%=user.pageCount%>网址 </div>
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
								<a class="col-sm-6 col-xs-12 no-padding" href="<%=p.url%>" target="_blank">
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
		
		<div class="col-sm-12">
		<!-- 翻页 -->
		<jsp:include page="part/pagination.jsp">
	    	<jsp:param name="url" value='<%=request.getContextPath()+"/index-hot-user.jsp"%>'/>
	    	<jsp:param name="count" value='<%=userCount %>'/>
	    	<jsp:param name="start" value='<%=start %>'/>
	    </jsp:include>
		<!-- 翻页end -->
		</div>
	</div>
	<!-- 主体内容 end-->
	<!-- 菜单 -->
 	<div class=" col-sm-3 no-padding" >
		<div class=" col-sm-10 col-sm-offset-1 no-padding" >
			<jsp:include page="part/hot-user.menu.slide.jsp"/>
		</div>
	</div>
	
	
	<input type="hidden" id="loginFlag" value='<%=loginFlag %>'>
	<input type="hidden" id="userID" value='<%=showUserID %>'>
	<input type="hidden" id="contextPath" value='<%=contextPath%>'>
	<input type="hidden" id="fid" value='<%=showFolderID%>'>
	<input type="hidden" id="curFolder" value='<%=showFolderName%>'>
	<input type="hidden" id="edit_pid" value="">
	<input type="hidden" id="edit_name" value="">
	<input type="hidden" id="edit_url" value="">
	<!-- hidden var end -->	<input type="hidden" id="pageID" value="index-hot-user">

	<!-- 弹出框开始 -->
	<jsp:include page="window/window.jsp" />
	<!-- 弹出框结束 -->
	<jsp:include page="part/foot.jsp" />
	<jsp:include page="part/importAtFoot.jsp" />
	
	<script src="<%=request.getContextPath()%>/myjs/pageMainApp.js" defer="defer"></script>
	<script src="<%=request.getContextPath()%>/myjs/common.js" defer="defer"></script>
</body>
</html>