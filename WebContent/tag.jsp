<%@page import="org.apache.tomcat.util.buf.UDecoder"%>
<%@page import="com.tgy.service.FolderService"%>
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
	UserDao uDao = new UserDao();

	TagService tService = new TagService();
	LinkService lService = new LinkService();

	FolderService fService = new FolderService();

	List<Folder> folders = U.paramList(request, "folders");
	String tagName = "";
	Tag tag = U.param(request, "tag", Tag.class);
	if(tag!=null) tagName = tag.name;
	
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

		<!-- 左侧 -->


		<!--------  tag  -------->
		<div class="  col-sm-2" style="border: 1px solid #eee;">
			<div>
				<table class="table">
					<thead>

					</thead>
					<tbody>
						<%
							int folderIndex = 1;
							for (Folder f : folders) {
								String folderName = f.name;
								User user = uDao.getByID(f.userID);
								int size = 0;
								if (f.pages != null)
									size = f.pages.size();
						%>
						<tr>
							<td>
								用户
								<a target="_blank"
								href="<%=request.getContextPath()%>/<%=user.name%>"><span
									style="font-size: 14px; font-weight: 300; margin-left: 3px;"> <%=user.name%></span></a>
								也收藏了 <a target="_blank"
								href="<%=request.getContextPath()%>/folder/<%=f.id%>"><span
									style="font-size: 14px; font-weight: 300; margin-left: 3px;"><%=folderName%></span></a>
								,包含网址 <%=size%> 个！</td>


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
		<div class="  col-sm-8" style="border: 1px solid #eee;">
			<h4>大家常用的<%=tagName %>网站</h4>
			<div>


				<table class="table">
					<thead>

					</thead>
					<tbody>
						<%
							List<Link> links = U.paramList(request, "links");
							int linkIndex = 1;
							for (Link link : links) {
								String titleName = link.title;
						%>
						<tr>
							<td><%=linkIndex++%></td>
							<td><a target="_blank" href=" <%=link.url%>"><span
									style="font-size: 14px; font-weight: 300; margin-left: 3px;"><%=titleName%></span></a></td>
							<td><a ng-click=" " href="#" class="col-sm-2"
								style="padding-left: 1px; padding-right: 1px; font-weight: bold;">
									<span style=""> + </span>
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