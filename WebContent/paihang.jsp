<%@page import="com.tgy.statistic.service.LinkService"%>
<%@page import="com.tgy.statistic.service.TagService"%>
<%@page import="com.tgy.service.UserService"%>
<%@page import="com.tgy.statistic.entity.Link"%>
<%@page import="com.tgy.statistic.entity.Tag"%>
<%@page import="com.tgy.dao.TagDao"%>
<%@page import="com.tgy.dao.LinkDao"%>
<%@page import="com.tgy.entity.User"%>
<%@page import="com.tgy.dao.UserDao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.tgy.web.vo.BookmarkData"%>
<%@page import="com.tgy.util.C"%>
<%@page import="com.tgy.entity.Folder"%>
<%@page import="com.tgy.util.U"%>
<%@page import="com.tgy.entity.Page"%>
<%@page import="java.util.List"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@ page language="java" pageEncoding="UTF-8"%>

<%
String userID = U.getUserID(request);
String contextPath = request.getContextPath();

UserService uService = new UserService();
TagService tService = new TagService();
LinkService lService = new LinkService();


%>

<!DOCTYPE html>
<html ng-app="pageMainApp" ng-controller="pageMainCtrl">
<head lang="en">
<jsp:include page="part/head-meta.jsp" />

<jsp:include page="part/importAtHead.jsp" />

</head>

 

<body>
	<jsp:include page="part/head.jsp" />

	<!-- 书签主页面开始 -->
	<div class="container col-sm-12 clearfix">
		<!-- 左侧  -->
		<div class="  col-sm-4" style="border: 1px solid #eee;">
			<h4>用户排行</h4>
			<div>


				<table class="table">
					<thead>

					</thead>
					<tbody>
						<%
							
							List<User> users = uService.list();
							int userIndex = 1;
							for (User u : users) {
								String userName = u.name;
						%>
						<tr>
							<td><%=userIndex++%></td>
							<td><a target="_blank" href="<%=request.getContextPath()%>/<%=userName%>"><span
									style="font-size: 14px; font-weight:300; margin-left: 3px;"><%=userName%></span></a></td>
							<td><a onclick=" " href="#" class="col-sm-2"
								style="padding-left: 1px; padding-right: 1px;font-weight: bold; "> <span
									style=" font-size: 18px;"> + </span>
							</a></td>
						</tr>


						<%
							}
						%>
					</tbody>
				</table>



			</div>

		</div>
		<!-- 左侧 -->


		<!--------  tag  -------->
		<div class="  col-sm-4" style="border: 1px solid #eee;">
			<h4>网址分类 排行</h4>
			<div>


				<table class="table">
					<thead>

					</thead>
					<tbody>
						<%
							
							List<Tag> tags = tService.list();
							int tagIndex = 1;
							for (Tag t : tags) {
								String tagName = t.name;
						%>
						<tr>
							<td><%=tagIndex++%></td>
							<td><a target="_blank" href="<%=request.getContextPath()%>/tag/<%=tagName%>"><span
									style="font-size: 14px; font-weight:300; margin-left: 3px;"><%=tagName%></span></a></td>
							<td><a onclick=" " href="#" class="col-sm-2"
								style="padding-left: 1px; padding-right: 1px;font-weight: bold; "> <span
									style="font-size: 18px; "> + </span>
							</a></td>
						</tr>


						<%
							}
						%>
					</tbody>
				</table>



			</div>

		</div>
		<!--------      --->


		<!--   -->
		<div class="  col-sm-4" style="border: 1px solid #eee;">
			<h4>网址排行</h4>
			<div>


				<table class="table">
					<thead>

					</thead>
					<tbody>
						<%
							List<Link> links = lService.list();
							int linkIndex = 1;  
							for (Link link : links) {
								if(StringUtils.isBlank(link.title)){
									continue;
								}
								String titleName = link.title;
								titleName = StringUtils.substring(titleName, 0,50);
						%>
						<tr>
							<td><%=linkIndex++%></td>
							<td><a target="_blank" href=" <%=link.url%>"><span
									style="font-size: 14px; font-weight:300; margin-left: 3px;"><%=titleName%></span></a></td>
							<td><a onclick=" " href="#" class="col-sm-2"
								style="padding-left: 1px; padding-right: 1px;font-weight: bold; "> <span
									style=" font-size: 18px; "> + </span> 
							</a></td>
						</tr>


						<%
							}
						%>
					</tbody>
				</table>



			</div>

		</div>
		<!--   -->

	</div>


</body>
<jsp:include page="part/importAtFoot.jsp" />
</html>