<%@page import="com.tgy.util.ConditionMap"%>
<%@page import="com.tgy.service.group.InterestGroupPageService"%>
<%@page import="com.tgy.entity.group.InterestGroupPage"%>
<%@page import="com.tgy.service.group.InterestGroupService"%>
<%@page import="com.tgy.entity.group.InterestGroup"%>
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
<%@include file="../part/bookmark-data.jsp" %> 
<%@include file="../part/show-user-data.jsp" %>
<%
 InterestGroupPageService ps = new InterestGroupPageService();
//UserService fs = new UserService();
//List<User> users = fs.list("-showTimes",10);
InterestGroupService service = new InterestGroupService();
List<InterestGroup> groups = service.list(null, null, 0);
%>

<!DOCTYPE html>
<html>
<head lang="en">
<jsp:include page="../part/head-meta.jsp" />
<jsp:include page="../part/importAtHead.jsp" />
</head>
<body>
	<jsp:include page="../part/head.jsp" />
	<jsp:include page="../part/public-tabs.jsp" />
	<div class="  col-sm-12" style="margin-top: 20px;"></div>

	<!-- 主体内容 -->
	<div class=" col-sm-9 no-padding container">
		<!-- one column start -->
		<div class="col-sm-6" style=" ">
			<%
		 	for (int i=0;i<groups.size();i+=2) {
		 		InterestGroup group = groups.get(i);
		 		String name = group.name;
				if (name != null && name.length() > 12) {
					name = name.substring(0, 12) + "..";
				}
		 	%> 
		 	
		 	<!--one info card -->
				<div class="col-sm-10  info-card no-padding">
					<div class="col-sm-12 head-info">
						<!-- head img -->
						<div class="col-sm-4">
							<a href="<%=request.getContextPath()%>/g/<%=group.id %>" >
							<%
								if(StringUtils.isBlank(group.headImgUrl)){
									%>
									<span class="glyphicon glyphicon-user" style="font-size: 40px;color: #eee;"></span>
									<%
								}
								else{
									%>
									<img style="height: 40px;width: 40px;" alt="已登录" src="<%=group.headImgUrl%>">
									<%
								}
							%> 
							</a>
						</div>
						<!-- user info -->
						<div class="col-sm-8 user-name">
							<a href="<%=request.getContextPath()%>/g/<%=group.id %>" >
								<span><%=name %></span></a>
							<div class="share-info"><span></span> <span><%=group.userCount %> 用户   0网址 </span> </div>
						</div>
					</div>
					
					<!-- links -->
					<div class="col-sm-12 info-card-pages ">
						<%
						List<InterestGroupPage> subPages = ps.list(new ConditionMap().add("groupID", group.id.toString()),null, 4);
						for(InterestGroupPage p :subPages){
					 		
							String pName = p.name; 
							if(StringUtils.isBlank(p.name)){
					 			pName = p.url;
					 			//continue;
					 		}
						%>
								<a class="col-sm-6 no-padding" href="<%=p.url%>" target="_blank">
									<span >
									<%=U.shortString(pName, 10)%>
									</span>
								</a>	
							<%
							}
						%>
					</div>
					<div class="col-sm-12 info-card-description ">
						这是一个专门做美剧资源的群组，欢迎大家加入。。
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
		 	for (int i=1;i<groups.size();i+=2) {
		 		InterestGroup group = groups.get(i);
		 		String name = group.name;
				if (name != null && name.length() > 12) {
					name = name.substring(0, 12) + "..";
				}
		 	%> 
		 	
		 	<!--one info card -->
				<div class="col-sm-10   info-card">
					<div class="col-sm-12">
						<div class="col-sm-4">
							<a href="<%=request.getContextPath()%>/g/<%=group.id %>" >
							<%
								if(StringUtils.isBlank(group.headImgUrl)){
									%>
									<span class="glyphicon glyphicon-user" style="font-size: 40px;color: #eee;"></span>
									<%
								}
								else{
									%>
									<img style="height: 40px;width: 40px;" alt="已登录" src="<%=group.headImgUrl%>">
									<%
								}
							%> 
							</a>
						</div>
						<div class="col-sm-8 user-name">
						<a href="<%=request.getContextPath()%>/g/<%=group.id %>" >
							<span><%=name %></span></a>
						<div class="share-info"><span></span> <span><%=group.showTimes %> 访问   0关注 </span> </div>
					</div>
					</div>
					
					<!-- links -->
					<div class="col-sm-12 info-card-pages ">
						<%
						List<InterestGroupPage> subPages = ps.list(new ConditionMap().add("groupID", group.id.toString()),null, 4);
						for(InterestGroupPage p :subPages){
							String pName = p.name; 
							if(StringUtils.isBlank(p.name)){
					 			pName = p.url;
					 			//continue;
					 		}
						%>
								<a class="col-sm-6 no-padding" href="<%=p.url%>" target="_blank">
									<span >
									<%=U.shortString(pName, 10)%>
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
			<jsp:include page="../part/group.index.menu.slide.jsp"/>
		</div>
	</div>
 
	<!-- 弹出框结束 -->
	<jsp:include page="../part/foot.jsp" />
	<jsp:include page="../part/importAtFoot.jsp" />
	
	<script src="<%=request.getContextPath()%>/myjs/common.js"></script>
</body>
</html>